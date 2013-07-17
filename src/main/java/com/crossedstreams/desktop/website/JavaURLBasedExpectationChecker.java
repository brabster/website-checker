package com.crossedstreams.desktop.website;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import javax.net.ssl.SSLProtocolException;

/**
 *
 * @author Paul Brabban <paul dot brabban at gmail dot com>
 */
public class JavaURLBasedExpectationChecker implements ExpectationChecker {

    public void check(UrlDefinition urlDefinition, Callback callback) {
        HttpURLConnection connection = null;
        UrlExpectation expect = urlDefinition.getExpectation();
        long start = System.currentTimeMillis();;
        try {
            start = System.currentTimeMillis();
            URL url = urlDefinition.getUrl().toURL();
            connection = HttpURLConnection.class.cast(url.openConnection());
            connection.setRequestMethod("GET");
            connection.setConnectTimeout((int)expect.getResponseInMillis());
            connection.connect();
            long elapsed = System.currentTimeMillis() - start;
            if (expect.getResponseHttpCode() != connection.getResponseCode()) {
                callback.onResult(new DefaultExpectationResult(false,
                        "expected HTTP " + expect.getResponseHttpCode() + 
                        " but received " + connection.getResponseCode()),
                        urlDefinition);
                return;
            }
            if (elapsed > expect.getResponseInMillis()) {
                callback.onResult(new DefaultExpectationResult(false,
                        "took " + elapsed + 
                        "ms, allowed " + expect.getResponseInMillis() + "ms"),
                        urlDefinition);
                return;
            }
            successCallback(callback, urlDefinition, elapsed);
        } catch (MalformedURLException ex) {
            errorCallback(callback, urlDefinition, ex);
        } catch (SSLProtocolException ex) {
            if (expect.allowCertificateErrors()) {
                certCallback(callback, urlDefinition, System.currentTimeMillis() - start);
            } else {
                errorCallback(callback, urlDefinition, ex);
            }
        } catch (IOException ex) {
            errorCallback(callback, urlDefinition, ex);
        } finally {
            if (connection != null) connection.disconnect();
        }
        
    }
    
    private void errorCallback(Callback callback, UrlDefinition def, Throwable ex) {
        callback.onResult(new DefaultExpectationResult(false, ex.getClass().getSimpleName() + " " + ex.getMessage()), def);
    }
    
    private void errorCallback(Callback callback, UrlDefinition def, String message) {
        callback.onResult(new DefaultExpectationResult(false, message), def);
    }
    
    private void certCallback(Callback callback, UrlDefinition def, long elapsed) {
        callback.onResult(new DefaultExpectationResult(true, "certificate error in " + elapsed + "ms"), def);
    }
    
    private void successCallback(Callback callback, UrlDefinition def, long elapsed) {
        callback.onResult(new DefaultExpectationResult(true, "status " + def.getExpectation().getResponseHttpCode() + " in " + elapsed + "ms"), def);
    }
    
}

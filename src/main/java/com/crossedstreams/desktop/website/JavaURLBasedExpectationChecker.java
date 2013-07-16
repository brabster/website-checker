package com.crossedstreams.desktop.website;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 *
 * @author Paul Brabban <paul dot brabban at gmail dot com>
 */
public class JavaURLBasedExpectationChecker implements ExpectationChecker {

    public void check(UrlDefinition urlDefinition, Callback callback) {
        HttpURLConnection connection = null;
        try {
            URL url = urlDefinition.getUrl().toURL();
            UrlExpectation expect = urlDefinition.getExpectation();
            connection = HttpURLConnection.class.cast(url.openConnection());
            connection.setRequestMethod("GET");
            connection.setConnectTimeout((int)expect.getResponseInMillis());
            long start = System.currentTimeMillis();
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
            callback.onResult(new DefaultExpectationResult(true, "status " + expect.getResponseHttpCode() + " in " + elapsed + "ms"), urlDefinition);
        } catch (MalformedURLException ex) {
            callback.onResult(new DefaultExpectationResult(false, ex.getMessage()), urlDefinition);
        } catch (IOException ex) {
            callback.onResult(new DefaultExpectationResult(false, ex.getMessage()), urlDefinition);
        } finally {
            connection.disconnect();
        }
        
    }
    
}

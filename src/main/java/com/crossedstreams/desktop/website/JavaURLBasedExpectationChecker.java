package com.crossedstreams.desktop.website;

import com.crossedstreams.desktop.website.ssl.PermissiveCertificateExposingTrustManager;
import com.crossedstreams.desktop.website.ssl.SSLContextInitialiser;
import com.crossedstreams.desktop.website.ssl.X509CertificateListener;
import com.crossedstreams.desktop.website.ssl.X509PathValidator;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.cert.CertificateExpiredException;
import java.security.cert.CertificateNotYetValidException;
import java.security.cert.X509Certificate;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import javax.net.ssl.SSLProtocolException;

/**
 *
 * @author Paul Brabban <paul dot brabban at gmail dot com>
 */
public class JavaURLBasedExpectationChecker implements ExpectationChecker {
    
    private UrlExpectation currentExpectation = null;
    
    private X509CertificateListener certListener = new X509CertificateListener() {
        
        private X509PathValidator pathValidator = new X509PathValidator();
        
        public void onCertificatePresentation(X509Certificate[] certs) {
            if (currentExpectation.acceptUntrustedCertificate() || !certificationPathIsTrusted(certs)) {
                throw new RuntimeException("Untrusted certificate");
            }
            
            Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
            cal.add(Calendar.DATE, currentExpectation.getCertificateValidForDays());
            
            if (!timeConstraintsMeetExpectation(cal.getTime(), certs)) {
                throw new RuntimeException("Certificate not valid for " + currentExpectation.getCertificateValidForDays() + " days");
            }
        }
        
        private boolean timeConstraintsMeetExpectation(Date validUntil, X509Certificate... certs) {
            for (X509Certificate cert:certs) {
                try {
                    cert.checkValidity();
                    cert.checkValidity(validUntil);
                } catch (CertificateExpiredException ex) {
                    return false;
                } catch (CertificateNotYetValidException ex) {
                    return false;
                }
            }
            return true;
        }
        
        private boolean certificationPathIsTrusted(X509Certificate... certs) {
            boolean isValid = pathValidator.isValid(certs);
            return isValid;
        }
    };
    
    {
        SSLContextInitialiser.initSslContext(new PermissiveCertificateExposingTrustManager(certListener));
    }
    
    public void check(UrlDefinition urlDefinition, Callback callback) {
        HttpURLConnection connection = null;
        this.currentExpectation = urlDefinition.getExpectation();
        long start = -1;
        try {
            start = System.currentTimeMillis();
            URL url = urlDefinition.getUrl().toURL();
            connection = HttpURLConnection.class.cast(url.openConnection());
            connection.setRequestMethod("GET");
            connection.setConnectTimeout((int)currentExpectation.getResponseInMillis());
            connection.connect();
            long elapsed = System.currentTimeMillis() - start;
            if (currentExpectation.getResponseHttpCode() != connection.getResponseCode()) {
                callback.onResult(new DefaultExpectationResult(false,
                        "expected HTTP " + currentExpectation.getResponseHttpCode() + 
                        " but received " + connection.getResponseCode()),
                        urlDefinition);
                return;
            }
            if (elapsed > currentExpectation.getResponseInMillis()) {
                callback.onResult(new DefaultExpectationResult(false,
                        "took " + elapsed + 
                        "ms, allowed " + currentExpectation.getResponseInMillis() + "ms"),
                        urlDefinition);
                return;
            }
            successCallback(callback, urlDefinition, elapsed);
        } catch (MalformedURLException ex) {
            errorCallback(callback, urlDefinition, ex);
        } catch (SSLProtocolException ex) {
            if (!currentExpectation.acceptUntrustedCertificate()) errorCallback(callback, urlDefinition, ex);
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

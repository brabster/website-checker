package com.crossedstreams.desktop.website.ssl;

import java.security.KeyStore;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

/**
 *
 * @author Paul Brabban <paul dot brabban at gmail dot com>
 */
public class SSLContextInitialiser {
    
    public static final String SSL_ALGORITHM = "TLS";
    
    private static X509TrustManager DEFAULT_TRUST_MANAGER = null;
    
    public static SSLSocketFactory getSSLSocketFactoryWithTrustManagers(TrustManager... managers) {
        try {
            KeyStore store = KeyStore.getInstance(KeyStore.getDefaultType());
            if (DEFAULT_TRUST_MANAGER == null) {
                TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
                tmf.init(store);
                DEFAULT_TRUST_MANAGER = (X509TrustManager) tmf.getTrustManagers()[0];
            }
            SSLContext ctx = SSLContext.getInstance(SSL_ALGORITHM);
            ctx.init(null, managers, null);
            return ctx.getSocketFactory();
        } catch (Exception e) {
            throw new RuntimeException("Unable to initialise SSL context", e);
        }
    }
    
    public static X509TrustManager getDefaultTrustManager() {
        return DEFAULT_TRUST_MANAGER;
    }
    
}

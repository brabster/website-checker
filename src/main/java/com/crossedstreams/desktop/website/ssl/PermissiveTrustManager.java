package com.crossedstreams.desktop.website.ssl;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.X509TrustManager;

/**
 * TrustManager that trusts all clients and servers
 * and delegates to the system default trust manager
 * for getAcceptedIssuers implementation;
 *
 * @author Paul Brabban <paul dot brabban at gmail dot com>
 */
public class PermissiveTrustManager implements X509TrustManager {
    
    @Override
    public void checkClientTrusted(X509Certificate[] xcs, String string) throws CertificateException {
    }

    @Override
    public void checkServerTrusted(X509Certificate[] xcs, String string) throws CertificateException {
    }

    @Override
    public X509Certificate[] getAcceptedIssuers() {
        return SSLContextInitialiser.getDefaultTrustManager().getAcceptedIssuers();
    }
    
}

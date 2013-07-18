package com.crossedstreams.desktop.website.ssl;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.net.ssl.X509TrustManager;

/**
 *
 * @author Paul Brabban <paul dot brabban at gmail dot com>
 */
public class PermissiveCertificateExposingTrustManager implements X509TrustManager {
    
    private final List<X509CertificateListener> listeners;

    public PermissiveCertificateExposingTrustManager(X509CertificateListener... listeners) {
        this.listeners = new ArrayList<X509CertificateListener>(listeners.length);
        Collections.addAll(this.listeners, listeners);
    }
    
    public void checkClientTrusted(X509Certificate[] xcs, String string) throws CertificateException {
        SSLContextInitialiser.getDefaultTrustManager().checkClientTrusted(xcs, string);
    }

    public void checkServerTrusted(X509Certificate[] xcs, String string) throws CertificateException {
        for (X509CertificateListener listener:listeners) {
            listener.onCertificatePresentation(xcs);
        }
    }

    public X509Certificate[] getAcceptedIssuers() {
        return SSLContextInitialiser.getDefaultTrustManager().getAcceptedIssuers();
    }
    
}

package com.crossedstreams.desktop.website;

import com.crossedstreams.desktop.website.ssl.X509CertificateListener;
import java.security.cert.X509Certificate;

/**
 *
 * @author Paul Brabban <paul dot brabban at gmail dot com>
 */
public class CertificateCheckingListener implements X509CertificateListener {

    public void onCertificatePresentation(X509Certificate[] certs) {
    }
    
}

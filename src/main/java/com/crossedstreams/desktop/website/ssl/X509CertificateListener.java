package com.crossedstreams.desktop.website.ssl;

import java.security.cert.X509Certificate;

/**
 *
 * @author Paul Brabban <paul dot brabban at gmail dot com>
 */
public interface X509CertificateListener {
    
    void onCertificatePresentation(X509Certificate[] certs);
    
}

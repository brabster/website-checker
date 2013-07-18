package com.crossedstreams.desktop.website.ssl;

import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Arrays;

/**
 *
 * @author Paul Brabban <paul dot brabban at gmail dot com>
 */
public class X509PathValidator {
    
    private final CertificateFactory certFactory;

    public X509PathValidator() {
        try {
            this.certFactory = CertificateFactory.getInstance("X.509");
        } catch (CertificateException ex) {
            throw new IllegalStateException("Unable to obtain certificate path validator", ex);
        }
    }

    public boolean isValid(X509Certificate... certs) {
        try {
            certFactory.generateCertPath(Arrays.asList(certs));
        } catch (CertificateException e) {
            return false;
        }
        return true;
    }
    
}

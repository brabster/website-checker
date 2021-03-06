/**
   Copyright 2013 Paul J Brabban

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */

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

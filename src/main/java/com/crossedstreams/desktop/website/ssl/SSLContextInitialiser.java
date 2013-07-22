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

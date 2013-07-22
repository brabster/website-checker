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

import javax.net.ssl.HttpsURLConnection;
import static org.junit.Assert.*;

import javax.net.ssl.SSLSocketFactory;
import org.junit.Test;

/**
 *
 * @author Paul Brabban <paul dot brabban at gmail dot com>
 */
public class SSLContextInitialiserTest {
    
    @Test
    public void givenCreateNewSslSocketFactory_thenUrlDefaultSslSocketFactoryUnchanged() throws Exception {
        SSLSocketFactory original = HttpsURLConnection.getDefaultSSLSocketFactory();
        SSLContextInitialiser.getSSLSocketFactoryWithTrustManagers(new PermissiveTrustManager());
        assertEquals("JVM SSL Context unchanged", original, HttpsURLConnection.getDefaultSSLSocketFactory());
    }
    
}
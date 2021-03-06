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

package com.crossedstreams.desktop.website;

import com.crossedstreams.desktop.website.ssl.PermissiveTrustManager;
import com.crossedstreams.desktop.website.ssl.SSLContextInitialiser;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;

/**
 *
 * @author Paul Brabban <paul dot brabban at gmail dot com>
 */
public class UrlConnectionFactory {
    
    /**
     * Protocols supported by this factory
     */
    public enum Protocol {
        HTTP("http"), HTTPS("https");
        
        private final String representation;
        
        private Protocol(String representation) {
            this.representation = representation;
        }
        
        /**
         * @param url to match against
         * @return true iff URL matches this protocol
         */
        public boolean matches(URL url) {
            return url.getProtocol().toLowerCase().equals(representation);
        }
    }
    
    private SSLSocketFactory sslSocketFactory =
            SSLContextInitialiser.getSSLSocketFactoryWithTrustManagers(new PermissiveTrustManager());
    
    /**
     * Get an HTTPUrlConnection instance for this URL
     * 
     * @param url to obtain instance for
     * @return URLConnection subclass
     * @throws IOException if an I/O related problem occurs
     */
    public HttpURLConnection getHttpUrlConnection(final URL url) throws IOException {
        Protocol protocol = Protocol.HTTP;
        if (!protocol.matches(url)) throw new IllegalArgumentException(url + " is not " + protocol);
        return HttpURLConnection.class.cast(url.openConnection());
    }
    
    /**
     * Get an HTTPSUrlConnection instance for this URL
     * 
     * @param url to obtain instance for
     * @return URLConnection subclass
     * @throws IOException if an I/O related problem occurs
     */
    public HttpsURLConnection getHttpsUrlConnection(final URL url) throws IOException {
        Protocol protocol = Protocol.HTTPS;
        if (!protocol.matches(url)) throw new IllegalArgumentException(url + " is not " + protocol);
        HttpsURLConnection connection = HttpsURLConnection.class.cast(url.openConnection());
        connection.setSSLSocketFactory(sslSocketFactory);
        return connection;
    }
    
    /**
     * Check which factory method to use for ths given URL.
     * 
     * @param url that requires HTTP or HTTPS protocol
     * @return true iff URL requires HTTPS protocol.
     */
    public boolean requiresHttps(final URL url) {
        return Protocol.HTTPS.matches(url);
    }
    
}

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
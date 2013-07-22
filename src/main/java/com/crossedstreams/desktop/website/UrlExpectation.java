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

import java.net.HttpURLConnection;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Paul Brabban <paul dot brabban at gmail dot com>
 */
public class UrlExpectation {
    
    public static UrlExpectation DEFAULT_EXPECTATION = new UrlExpectation(
            TimeUnit.MILLISECONDS.convert(5, TimeUnit.SECONDS),
            HttpURLConnection.HTTP_OK,
            false,
            7);

    private final long responseInMillis;
    private final int responseHttpCode;
    private final boolean acceptUntrustedCertificate;
    private final int certificateValidForDays;

    public UrlExpectation(long responseInMillis, int responseHttpCode, boolean acceptUntrustedCertificate, int certificateValidForDays) {
        this.responseInMillis = responseInMillis;
        this.responseHttpCode = responseHttpCode;
        this.acceptUntrustedCertificate = acceptUntrustedCertificate;
        this.certificateValidForDays = certificateValidForDays;
    }

    public long getResponseInMillis() {
        return responseInMillis;
    }

    public int getResponseHttpCode() {
        return responseHttpCode;
    }

    public boolean acceptUntrustedCertificate() {
        return acceptUntrustedCertificate;
    }

    public int getCertificateValidForDays() {
        return certificateValidForDays;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + (int) (this.responseInMillis ^ (this.responseInMillis >>> 32));
        hash = 67 * hash + this.responseHttpCode;
        hash = 67 * hash + (this.acceptUntrustedCertificate ? 1 : 0);
        hash = 67 * hash + this.certificateValidForDays;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UrlExpectation other = (UrlExpectation) obj;
        if (this.responseInMillis != other.responseInMillis) {
            return false;
        }
        if (this.responseHttpCode != other.responseHttpCode) {
            return false;
        }
        if (this.acceptUntrustedCertificate != other.acceptUntrustedCertificate) {
            return false;
        }
        if (this.certificateValidForDays != other.certificateValidForDays) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "UrlExpectation{" + "responseInMillis=" + responseInMillis + ", responseHttpCode=" + responseHttpCode + ", acceptUntrustedCertificate=" + acceptUntrustedCertificate + ", certificateValidForDays=" + certificateValidForDays + '}';
    }

}

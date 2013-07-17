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
            false);

    private final long responseInMillis;
    private final int responseHttpCode;
    private final boolean allowCertificateErrors;

    public UrlExpectation(long responseInMillis, int responseHttpCode, boolean allowCertificateErrors) {
        this.responseInMillis = responseInMillis;
        this.responseHttpCode = responseHttpCode;
        this.allowCertificateErrors = allowCertificateErrors;
    }

    public long getResponseInMillis() {
        return responseInMillis;
    }

    public int getResponseHttpCode() {
        return responseHttpCode;
    }

    public boolean allowCertificateErrors() {
        return allowCertificateErrors;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + (int) (this.responseInMillis ^ (this.responseInMillis >>> 32));
        hash = 97 * hash + this.responseHttpCode;
        hash = 97 * hash + (this.allowCertificateErrors ? 1 : 0);
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
        if (this.allowCertificateErrors != other.allowCertificateErrors) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "UrlExpectation{" + "responseInMillis=" + responseInMillis + ", responseHttpCode=" + responseHttpCode + ", allowCertificateErrors=" + allowCertificateErrors + '}';
    }
    
}

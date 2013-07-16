package com.crossedstreams.desktop.website;

import java.util.concurrent.TimeUnit;

/**
 *
 * @author Paul Brabban <paul dot brabban at gmail dot com>
 */
public class UrlExpectation {
    
    public static UrlExpectation DEFAULT_EXPECTATION = new UrlExpectation(TimeUnit.MILLISECONDS.convert(5, TimeUnit.SECONDS), 200);

    private final long responseInMillis;
    private final int responseHttpCode;

    public UrlExpectation(long responseInMillis, int responseHttpCode) {
        this.responseInMillis = responseInMillis;
        this.responseHttpCode = responseHttpCode;
    }

    public long getResponseInMillis() {
        return responseInMillis;
    }

    public int getResponseHttpCode() {
        return responseHttpCode;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + (int) (this.responseInMillis ^ (this.responseInMillis >>> 32));
        hash = 19 * hash + this.responseHttpCode;
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
        return true;
    }

    @Override
    public String toString() {
        return "UrlExpectation{" + "responseInMillis=" + responseInMillis + ", responseHttpCode=" + responseHttpCode + '}';
    }

}

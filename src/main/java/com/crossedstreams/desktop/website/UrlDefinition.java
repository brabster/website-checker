package com.crossedstreams.desktop.website;

import java.net.URI;

/**
 *
 * @author Paul Brabban <paul dot brabban at gmail dot com>
 */
public class UrlDefinition {
    
    private URI url;
    private String label;
    private UrlExpectation expectation;

    public UrlDefinition(URI url, String label, UrlExpectation expectation) {
        this.url = url;
        this.expectation = expectation;
        this.label = label;
    }

    public URI getUrl() {
        return url;
    }

    public UrlExpectation getExpectation() {
        return expectation;
    }
    
    public String getLabel() {
        return label;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + (this.url != null ? this.url.hashCode() : 0);
        hash = 71 * hash + (this.label != null ? this.label.hashCode() : 0);
        hash = 71 * hash + (this.expectation != null ? this.expectation.hashCode() : 0);
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
        final UrlDefinition other = (UrlDefinition) obj;
        if (this.url != other.url && (this.url == null || !this.url.equals(other.url))) {
            return false;
        }
        if ((this.label == null) ? (other.label != null) : !this.label.equals(other.label)) {
            return false;
        }
        if (this.expectation != other.expectation && (this.expectation == null || !this.expectation.equals(other.expectation))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "UrlDefinition{" + "url=" + url + ", label=" + label + ", expectation=" + expectation + '}';
    }

}

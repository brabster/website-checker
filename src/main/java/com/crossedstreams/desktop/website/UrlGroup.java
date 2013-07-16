package com.crossedstreams.desktop.website;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Paul Brabban <paul dot brabban at gmail dot com>
 */
public class UrlGroup {
    
    private String label;
    private UrlExpectation defaultExpectations;
    private Collection<UrlDefinition> urlDefinitions;

    public UrlGroup(String label, UrlExpectation defaultExpectations, List<UrlDefinition> urlDefinitions) {
        this.label = label;
        this.defaultExpectations = defaultExpectations;
        this.urlDefinitions = Collections.unmodifiableCollection(new ArrayList<UrlDefinition>(urlDefinitions));
    }

    public String getLabel() {
        return label;
    }

    public UrlExpectation getDefaultExpectations() {
        return defaultExpectations;
    }

    public Collection<UrlDefinition> getUrlDefinitions() {
        return urlDefinitions;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + (this.label != null ? this.label.hashCode() : 0);
        hash = 37 * hash + (this.defaultExpectations != null ? this.defaultExpectations.hashCode() : 0);
        hash = 37 * hash + (this.urlDefinitions != null ? this.urlDefinitions.hashCode() : 0);
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
        final UrlGroup other = (UrlGroup) obj;
        if ((this.label == null) ? (other.label != null) : !this.label.equals(other.label)) {
            return false;
        }
        if (this.defaultExpectations != other.defaultExpectations && (this.defaultExpectations == null || !this.defaultExpectations.equals(other.defaultExpectations))) {
            return false;
        }
        if (this.urlDefinitions != other.urlDefinitions && (this.urlDefinitions == null || !this.urlDefinitions.equals(other.urlDefinitions))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "UrlGroup{" + "label=" + label + ", defaultExpectations=" + defaultExpectations + ", urlDefinitions=" + urlDefinitions + '}';
    }

}

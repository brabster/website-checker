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

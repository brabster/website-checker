package com.crossedstreams.desktop.website;

/**
 * Checks whether the expectation for a URL definition is met.
 *
 * @author Paul Brabban <paul dot brabban at gmail dot com>
 */
public interface ExpectationChecker {
    
    void check(UrlDefinition urlDefinition, Callback callback);
    
    interface Callback {
        void onResult(ExpectationResult result, UrlDefinition def);
    }
    
    interface ExpectationResult {
        boolean isMet();
        String getExplanation();
    }
    
    class DefaultExpectationResult implements ExpectationResult {
        private boolean met;
        private String explanation;

        public DefaultExpectationResult(boolean met, String explanation) {
            this.met = met;
            this.explanation = explanation;
        }

        public boolean isMet() {
            return met;
        }

        public String getExplanation() {
            return explanation;
        }

        @Override
        public int hashCode() {
            int hash = 5;
            hash = 79 * hash + (this.met ? 1 : 0);
            hash = 79 * hash + (this.explanation != null ? this.explanation.hashCode() : 0);
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
            final DefaultExpectationResult other = (DefaultExpectationResult) obj;
            if (this.met != other.met) {
                return false;
            }
            if ((this.explanation == null) ? (other.explanation != null) : !this.explanation.equals(other.explanation)) {
                return false;
            }
            return true;
        }
        
        @Override
        public String toString() {
            return "DefaultExpectationResult{" + "met=" + met + ", explanation=" + explanation + '}';
        }
        
    }
    
}

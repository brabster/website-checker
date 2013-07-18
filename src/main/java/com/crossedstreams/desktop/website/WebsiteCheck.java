package com.crossedstreams.desktop.website;

import com.crossedstreams.desktop.website.ExpectationChecker.Callback;
import com.crossedstreams.desktop.website.ExpectationChecker.ExpectationResult;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Collection;

/**
 *
 * @author Paul Brabban <paul dot brabban at gmail dot com>
 */
public class WebsiteCheck {

    private Collection<UrlGroup> groups;
    private ExpectationChecker checker;

    public WebsiteCheck(Collection<UrlGroup> groups, ExpectationChecker checker) {
        this.groups = groups;
        this.checker = checker;
    }

    public void check(Callback callback) {
        for (UrlGroup group : groups) {
            for (UrlDefinition def : group.getUrlDefinitions()) {
                checker.check(def, callback);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        final Callback callback = new Callback() {
            public void onResult(ExpectationResult result, UrlDefinition def) {
                System.out.println(String.format("%s %s (%s) %s",
                        (result.isMet()) ? "OK " : "ERR",
                        def.getLabel(),
                        def.getUrl(),
                        result.getExplanation()));
            }
        };

        String jsonLocation = (args.length > 0) ? args[0] : "./urls.json";
        InputStream is = WebsiteCheck.class.getClassLoader().getResourceAsStream(jsonLocation);
        if (is == null) throw new RuntimeException("Unable to load URL data from " + jsonLocation);
        Reader reader = new InputStreamReader(is, "utf-8");
        System.out.println("Starting Check");
        new WebsiteCheck(new JsonParser().parseUrlGroups(reader), new JavaURLBasedExpectationChecker()).check(callback);
        System.out.println("Check Complete");
    }

    public Collection<UrlGroup> getGroups() {
        return groups;
    }

    public ExpectationChecker getChecker() {
        return checker;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + (this.groups != null ? this.groups.hashCode() : 0);
        hash = 53 * hash + (this.checker != null ? this.checker.hashCode() : 0);
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
        final WebsiteCheck other = (WebsiteCheck) obj;
        if (this.groups != other.groups && (this.groups == null || !this.groups.equals(other.groups))) {
            return false;
        }
        if (this.checker != other.checker && (this.checker == null || !this.checker.equals(other.checker))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "WebsiteCheck{" + "groups=" + groups + ", checker=" + checker + '}';
    }
}

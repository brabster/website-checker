package com.crossedstreams.desktop.website;

import com.crossedstreams.desktop.website.ExpectationChecker.Callback;
import java.io.InputStreamReader;
import java.io.Reader;
import org.junit.Test;

/**
 *
 * @author Paul Brabban <paul dot brabban at gmail dot com>
 */
public class ExpectationCheckerTest {
    
    @Test
    public void testCheck() throws Exception {
        Reader reader = new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream("urls.json"), "utf-8");
        new WebsiteCheck(new JsonParser().parseUrlGroups(reader), new JavaURLBasedExpectationChecker()).check(new Callback() {
            public void onResult(ExpectationChecker.ExpectationResult result, UrlDefinition def) {
                System.out.println(result + "," + def);
            }
        });
    }

    
}
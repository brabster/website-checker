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
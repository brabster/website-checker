/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crossedstreams.desktop.website;

import java.io.InputStreamReader;
import java.io.Reader;
import org.junit.Test;

/**
 *
 * @author Paul Brabban <paul dot brabban at gmail dot com>
 */
public class JsonParserTest {
    
    @Test
    public void testParseUrlGroups() throws Exception {
        Reader reader = new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream("urls.json"), "utf-8");
        System.out.println(new JsonParser().parseUrlGroups(reader));
    }
}
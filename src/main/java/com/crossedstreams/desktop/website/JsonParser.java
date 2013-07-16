package com.crossedstreams.desktop.website;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.Reader;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Parse website definitions from Json format
 *
 * @author Paul Brabban <paul dot brabban at gmail dot com>
 */
public class JsonParser {
    
    /**
     * Parse JSON formatted input.
     * 
     * @param from JSON formatted data as a reader stream
     * @return collection build from incoming data
     * @throws IOException if the attempt to read the input fails
     */
    public Collection<UrlGroup> parseUrlGroups(Reader from) throws IOException {
        ObjectMapper jsonObjectMapper = new ObjectMapper();        
        JsonNode rootNode = jsonObjectMapper.readTree(from);

        List<UrlGroup> urlGroups = new ArrayList<UrlGroup>();
        if (!rootNode.isArray()) throw new IllegalArgumentException("Invalid input, root node should be an array of groups");
        for (JsonNode groupJson:rootNode) {
            urlGroups.add(buildGroup(groupJson));
        }
        return urlGroups;
    }
    
    private UrlGroup buildGroup(JsonNode groupJson) {
        String label = groupJson.path("label").asText();
        UrlExpectation expectation = buildExpectation(groupJson.path("expect"));
        JsonNode urls = groupJson.path("urls");
        List<UrlDefinition> defs = new ArrayList<UrlDefinition>(urls.size());
        for (JsonNode urlNode:urls) {
            defs.add(buildUrlDef(urlNode));
        }
        return new UrlGroup(label, expectation, defs);
    }
    
    private UrlDefinition buildUrlDef(JsonNode urlJson) {
        String url = urlJson.path("url").asText();
        String label = urlJson.path("label").asText();
        UrlExpectation expectation = buildExpectation(urlJson.path("expect"));
        return new UrlDefinition(URI.create(url), label, expectation);
    }
    
    private UrlExpectation buildExpectation(JsonNode expectJson) {
        if (expectJson.isMissingNode()) return UrlExpectation.DEFAULT_EXPECTATION;
        
        long respondsInMillis = expectJson.path("faster-than-millis")
                .asLong(UrlExpectation.DEFAULT_EXPECTATION.getResponseInMillis());
        
        int statusCode = expectJson.path("status_code")
                .asInt(UrlExpectation.DEFAULT_EXPECTATION.getResponseHttpCode());
        
        return new UrlExpectation(respondsInMillis, statusCode);
    }
        
}

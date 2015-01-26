package io.tew88.shorturl.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Value;

@Value 
public class UrlMapping {
    
    @JsonProperty
    String url;
    
    @JsonProperty
    String shortUrl;
}
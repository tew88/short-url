package io.tew88.shorturl;

import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import io.dropwizard.Configuration;

@Getter
@Setter
public class ShortUrlConfiguration extends Configuration {
    
    @Valid
    @JsonProperty
    private RedisConfiguration redis;
}
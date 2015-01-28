package io.tew88.shorturl;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class RedisConfiguration {
    
    @NotEmpty
    @JsonProperty
    String hostname;
    
    @Min(1)
    @Max(65535)
    @JsonProperty
    Integer port;
}
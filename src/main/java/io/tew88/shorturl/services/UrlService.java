package io.tew88.shorturl.services;

import java.math.BigInteger;

import org.apache.commons.validator.routines.UrlValidator;

import io.tew88.shorturl.domain.UrlMapping;

import com.google.common.base.Optional;
import com.google.common.io.BaseEncoding;

public abstract class UrlService {
    
    private static final String[] SCHEMES = {"http", "https"};
    private static final UrlValidator URL_VALIDATOR = new UrlValidator(SCHEMES);
    
    public abstract Optional<UrlMapping> saveUrl(final String url);
    
    public abstract Optional<UrlMapping> getUrl(final String shortUrl);
    
    // TODO: URL encoding should be handled by an injected dependency
    protected String encodeUrl(final String url) {
        return BaseEncoding.base64Url().encode(
                BigInteger.valueOf(url.hashCode()).toByteArray()).replaceAll("=", "");
    }
    
    protected boolean isValidUrl(final String url) {
        return URL_VALIDATOR.isValid(url);
    }
}
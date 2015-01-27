package io.tew88.shorturl.services;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Singleton;

import io.tew88.shorturl.domain.UrlMapping;

import com.google.common.base.Optional;

@Singleton
public class HashMapUrlServiceImpl extends UrlService {
    
    private Map<String, UrlMapping> shortUrlToUrlMap = new HashMap<>();

    @Override
    public Optional<UrlMapping> saveUrl(String url) {
        
        if (isValidUrl(url)) {
            String shortUrl = encodeUrl(url);
            UrlMapping urlMapping = new UrlMapping(url, shortUrl);
            shortUrlToUrlMap.put(shortUrl,  urlMapping);
            return Optional.of(urlMapping);
        }
        
        return Optional.absent();
    }

    @Override
    public Optional<UrlMapping> getUrl(String shortUrl) {
        return Optional.fromNullable(shortUrlToUrlMap.get(shortUrl));
    }
}
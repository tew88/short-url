package io.tew88.shorturl.services;

import javax.inject.Inject;

import redis.clients.jedis.Jedis;
import io.tew88.shorturl.domain.UrlMapping;

import com.google.common.base.Optional;

public class RedisUrlServiceImpl extends UrlService {
    
    private final Jedis jedis;
    
    @Inject
    public RedisUrlServiceImpl(final Jedis jedis) {
        this.jedis = jedis;
    }

    @Override
    public Optional<UrlMapping> saveUrl(String url) {
        
        if (isValidUrl(url)) {
            String shortUrl = encodeUrl(url);
            UrlMapping urlMapping = new UrlMapping(url, shortUrl);
            
            persistUrlMappingToRedis(shortUrl, shortUrl);
            
            return Optional.of(urlMapping);
        }
        
        return Optional.absent();
    }

    @Override
    public Optional<UrlMapping> getUrl(String shortUrl) {
        if (jedis.exists(shortUrl)) {
            UrlMapping urlMapping = new UrlMapping(jedis.get(shortUrl), shortUrl);
            return Optional.of(urlMapping);
        }
        return Optional.absent();
    }

    private void persistUrlMappingToRedis(final String shortUrl, final String url) {
        jedis.set(shortUrl, url);
        
    }
}
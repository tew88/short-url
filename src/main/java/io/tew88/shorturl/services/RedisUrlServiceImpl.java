package io.tew88.shorturl.services;

import javax.inject.Inject;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import io.tew88.shorturl.domain.UrlMapping;

import com.google.common.base.Optional;

public class RedisUrlServiceImpl extends UrlService {
    
    private final JedisPool jedisPool;
    
    @Inject
    public RedisUrlServiceImpl(final JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    @Override
    public Optional<UrlMapping> saveUrl(String url) {
        
        if (isValidUrl(url)) {
            String shortUrl = encodeUrl(url);
            UrlMapping urlMapping = new UrlMapping(url, shortUrl);
            
            try (Jedis jedis = jedisPool.getResource()) {
                jedis.set(shortUrl, url);
            }
            
            return Optional.of(urlMapping);
        }
        
        return Optional.absent();
    }

    @Override
    public Optional<UrlMapping> getUrl(String shortUrl) {
        try (Jedis jedis = jedisPool.getResource()) {
            if (jedis.exists(shortUrl)) {
                UrlMapping urlMapping = new UrlMapping(jedis.get(shortUrl), shortUrl);
                return Optional.of(urlMapping);
            }
            return Optional.absent();
        }
    }
}
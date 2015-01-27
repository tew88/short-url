package io.tew88.shorturl;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import io.tew88.shorturl.services.RedisUrlServiceImpl;
import io.tew88.shorturl.services.UrlService;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;

public class ApplicationModule extends AbstractModule {
    
    private static final JedisPool JEDIS_POOL =
            new JedisPool(new JedisPoolConfig(), "localhost", 6379);

    @Override
    protected void configure() {
        bind(UrlService.class).to(RedisUrlServiceImpl.class);
    }
    
    @Provides
    public Jedis provideJedisFromPool() {
        return JEDIS_POOL.getResource();
    }
}
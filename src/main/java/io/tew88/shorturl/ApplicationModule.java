package io.tew88.shorturl;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import io.tew88.shorturl.services.RedisUrlServiceImpl;
import io.tew88.shorturl.services.UrlService;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;

public class ApplicationModule extends AbstractModule {
    
    @Override
    protected void configure() {
        bind(UrlService.class).to(RedisUrlServiceImpl.class);
    }
    
    // This should be annotated as @Singleton, but the application configuration isn't in scope
    // at the point that Guice builds its eager singletons
    @Provides
    public JedisPool provideJedisPool(ShortUrlConfiguration configuration) {
        RedisConfiguration redis = configuration.getRedis();
        return new JedisPool(new JedisPoolConfig(), redis.getHostname(), redis.getPort());
    }
}
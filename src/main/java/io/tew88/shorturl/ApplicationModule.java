package io.tew88.shorturl;

import io.tew88.shorturl.services.HashMapUrlServiceImpl;
import io.tew88.shorturl.services.UrlService;

import com.google.inject.AbstractModule;

public class ApplicationModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(UrlService.class).to(HashMapUrlServiceImpl.class);
    }
}
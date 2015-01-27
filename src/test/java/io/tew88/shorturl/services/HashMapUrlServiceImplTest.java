package io.tew88.shorturl.services;

import io.tew88.shorturl.domain.UrlMapping;

import org.junit.Before;
import org.junit.Test;

import com.google.common.base.Optional;

import static org.assertj.core.api.Assertions.*;

public class HashMapUrlServiceImplTest {
    
    private static final String VALID_TEST_URL = "http://www.google.com";
    private static final String INVALID_TEST_URL = "http;//www.google.com";
    
    private HashMapUrlServiceImpl urlService;
    
    @Before
    public void setup() {
        urlService = new HashMapUrlServiceImpl();
    }
    
    @Test
    public void shouldShortenUrlAndSaveTheMappingWhenTheProvidedUrlIsValid() {
        Optional<UrlMapping> urlMapping = urlService.saveUrl(VALID_TEST_URL);
        
        assertThat(urlMapping.isPresent()).isTrue();
        assertThat(urlMapping.get().getUrl()).isEqualTo(VALID_TEST_URL);
    }
    
    @Test
    public void shouldReturnOptionalAbsentWhenTheProvidedUrlIsInvalid() {
        Optional<UrlMapping> urlMapping = urlService.saveUrl(INVALID_TEST_URL);
        
        assertThat(urlMapping.isPresent()).isFalse();
    }
    
    @Test
    public void shouldReturnUrlMappingForGivenShortUrlWhenItExists() {
        Optional<UrlMapping> saveUrlMapping = urlService.saveUrl(VALID_TEST_URL);
        String shortUrl = saveUrlMapping.get().getShortUrl();
        
        Optional<UrlMapping> lookupUrlMapping = urlService.getUrl(shortUrl);
        assertThat(lookupUrlMapping.isPresent()).isTrue();
        assertThat(lookupUrlMapping.get().getUrl()).isEqualTo(VALID_TEST_URL);
    }
}
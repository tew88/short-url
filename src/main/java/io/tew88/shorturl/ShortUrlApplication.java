package io.tew88.shorturl;

import com.hubspot.dropwizard.guice.GuiceBundle;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class ShortUrlApplication extends Application<ShortUrlConfiguration>{
    
    private static final String SERVICE_NAME = "short-url";
    
    private GuiceBundle<ShortUrlConfiguration> guiceBundle;
    
    public static void main(String[] args) throws Exception {
        new ShortUrlApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<ShortUrlConfiguration> bootstrap) {
        
        guiceBundle = GuiceBundle.<ShortUrlConfiguration>newBuilder()
                .addModule(new ApplicationModule())
                .enableAutoConfig(getClass().getPackage().getName())
                .setConfigClass(ShortUrlConfiguration.class)
                .build();
        
        bootstrap.addBundle(guiceBundle);
    }
    
    @Override
    public String getName() {
        return SERVICE_NAME;
    }

    @Override
    public void run(ShortUrlConfiguration configuration, Environment environment) throws Exception {
    }
}
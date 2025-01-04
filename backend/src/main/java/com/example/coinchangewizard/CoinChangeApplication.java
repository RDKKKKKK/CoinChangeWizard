package com.example.coinchangewizard;

import com.example.coinchangewizard.core.CoinChangeService;
import com.example.coinchangewizard.resource.CoinChangeResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class CoinChangeApplication extends Application<CoinChangeConfiguration> {

    public static void main(String[] args) throws Exception {
        new CoinChangeApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<CoinChangeConfiguration> bootstrap) {

    }

    @Override
    public void run(CoinChangeConfiguration configuration, Environment environment) {
        CoinChangeService service = new CoinChangeService();
        environment.jersey().register(new CoinChangeResource(service));
    }
}
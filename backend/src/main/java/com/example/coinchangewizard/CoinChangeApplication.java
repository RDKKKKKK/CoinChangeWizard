package com.example.coinchangewizard;

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

    }
}
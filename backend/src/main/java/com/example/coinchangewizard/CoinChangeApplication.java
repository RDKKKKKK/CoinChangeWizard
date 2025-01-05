package com.example.coinchangewizard;

import com.example.coinchangewizard.core.CoinChangeService;
import com.example.coinchangewizard.resource.CoinChangeResource;
import com.fasterxml.jackson.databind.module.SimpleModule;
import io.dropwizard.Application;
import io.dropwizard.jackson.DiscoverableSubtypeResolver;
import io.dropwizard.jetty.HttpConnectorFactory;
import io.dropwizard.server.DefaultServerFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class CoinChangeApplication extends Application<CoinChangeConfiguration> {

    public static void main(String[] args) throws Exception {
        new CoinChangeApplication().run(args);
    }


    @Override
    public void initialize(Bootstrap<CoinChangeConfiguration> bootstrap) {
        bootstrap.getObjectMapper().registerModule(new SimpleModule()
                .registerSubtypes(HttpConnectorFactory.class, DefaultServerFactory.class));
    }



    @Override
    public void run(CoinChangeConfiguration configuration, Environment environment) {
        CoinChangeService service = new CoinChangeService();
        environment.jersey().register(new CoinChangeResource(service));
    }
}
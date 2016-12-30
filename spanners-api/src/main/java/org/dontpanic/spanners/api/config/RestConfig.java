package org.dontpanic.spanners.api.config;

import org.dontpanic.spanners.api.data.Spanner;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

/**
 * Custom configuration for Spring Data REST
 * Created by stevie on 10/06/16.
 */
@Configuration
@EnableDiscoveryClient
public class RestConfig extends RepositoryRestConfigurerAdapter {

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.exposeIdsFor(Spanner.class);
    }
}

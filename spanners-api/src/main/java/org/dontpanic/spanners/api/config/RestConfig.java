package org.dontpanic.spanners.api.config;

import org.dontpanic.spanners.api.data.Spanner;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;

/**
 * Custom configuration for Spring Data REST
 * Created by stevie on 10/06/16.
 */
@Configuration
public class RestConfig extends RepositoryRestMvcConfiguration {

    @Override
    protected void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.exposeIdsFor(Spanner.class);
    }
}

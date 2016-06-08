package org.dontpanic.spanners.springmvc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Configuration of connections to external services
 * Created by stevie on 08/06/16.
 */
@Configuration
public class ServiceConfig {

    @Bean
    public RestTemplate configureRestTemplate() {
        return new RestTemplate();
    }

}

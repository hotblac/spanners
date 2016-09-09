package org.dontpanic.spanners.springmvc.config;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.ribbon.RibbonClientHttpRequestFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configure the Rest client
 * Created by stevie on 09/09/16.
 */
@Configuration
@EnableDiscoveryClient
public class RestClientConfig {

    private Logger logger = Logger.getLogger(RestClientConfig.class);

    @Autowired(required = false)
    private RibbonClientHttpRequestFactory ribbonRequestFactory;

    /**
     * Use Ribbon load balancer to resolve service endpoints
     */
    @Bean
    public RestTemplateBuilder restTemplateBuilder() {

        RestTemplateBuilder builder = new RestTemplateBuilder();
        if (ribbonRequestFactory != null) {
            builder = builder.requestFactory(ribbonRequestFactory);
        } else {
            logger.warn("Ribbon Http client disabled. Service endpoints will not be resolved by Eureka.");
        }
        return builder;
    }

}

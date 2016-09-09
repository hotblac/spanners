package org.dontpanic.spanners.springmvc.config;

import org.springframework.cloud.netflix.ribbon.RibbonClientHttpRequestFactory;
import org.springframework.cloud.netflix.ribbon.SpringClientFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by stevie on 09/09/16.
 */
@Configuration
public class RestClientConfig {

    @Bean
    public RibbonClientHttpRequestFactory ribbonClientHttpRequestFactory(SpringClientFactory springClientFactory) {
        return new RibbonClientHttpRequestFactory(springClientFactory);
    }

}

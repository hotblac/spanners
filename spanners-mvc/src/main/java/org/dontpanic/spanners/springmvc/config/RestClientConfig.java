package org.dontpanic.spanners.springmvc.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;

/**
 * Configure the Rest client
 * Created by stevie on 09/09/16.
 */
@Configuration
public class RestClientConfig {

    @Bean
    public RestTemplateBuilder restTemplateBuilder(ClientHttpRequestFactory requestFactory) {
        return new RestTemplateBuilder().requestFactory(requestFactory);
    }

}

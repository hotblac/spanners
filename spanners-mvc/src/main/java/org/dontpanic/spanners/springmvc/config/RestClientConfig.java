package org.dontpanic.spanners.springmvc.config;

import com.netflix.client.http.HttpRequest;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.ribbon.RibbonClientHttpRequestFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Configure the Rest client
 *
 * Implementation based on spring-cloud-netflix-core RibbonClientConfig.
 *
 * Created by stevie on 09/09/16.
 */
@Configuration
@ConditionalOnClass(HttpRequest.class)
@ConditionalOnProperty(value = "ribbon.http.client.enabled", matchIfMissing = false)
@EnableDiscoveryClient
public class RestClientConfig {

    /**
     * Customize the RestTemplate to use Ribbon load balancer to resolve service endpoints
     */
    @Bean
    public RestTemplateCustomizer ribbonClientRestTemplateCustomizer(
            final RibbonClientHttpRequestFactory ribbonClientHttpRequestFactory) {
        return new RestTemplateCustomizer() {
            @Override
            public void customize(RestTemplate restTemplate) {
                restTemplate.setRequestFactory(ribbonClientHttpRequestFactory);
            }
        };
    }

}

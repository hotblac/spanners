package org.dontpanic.spanners.springmvc.config;

import org.springframework.context.annotation.*;

@Configuration
@ImportResource({"classpath:/spring-hibernate.xml",
                 "classpath:/spring-dataSources.xml",
                 "classpath:spring-properties.xml"})
public class RootConfig {

}
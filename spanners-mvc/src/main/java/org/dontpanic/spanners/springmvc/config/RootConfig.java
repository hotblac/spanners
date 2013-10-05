package org.dontpanic.spanners.springmvc.config;

import org.springframework.context.annotation.*;

@Configuration
@ImportResource({"classpath:/spring-hibernate.xml",
                 "classpath:/spring-dataSources.xml"})
@ComponentScan(basePackages = { "org.dontpanic.spanners.springmvc" })
public class RootConfig {

}
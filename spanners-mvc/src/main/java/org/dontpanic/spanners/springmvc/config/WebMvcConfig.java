package org.dontpanic.spanners.springmvc.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

@Configuration
public class WebMvcConfig {

	private static final String MESSAGE_SOURCE = "/WEB-INF/i18n/messages";
	private static final String TILES = "/WEB-INF/tiles/tiles.xml";
	private static final String VIEWS = "/WEB-INF/views/**/views.xml";

	@Bean(name = "messageSource")
	public MessageSource configureMessageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename(MESSAGE_SOURCE);
		messageSource.setCacheSeconds(5);
		return messageSource;
	}

	@Bean
	public TilesViewResolver configureTilesViewResolver() {
		return new TilesViewResolver();
	}

	@Bean
	public TilesConfigurer configureTilesConfigurer() {
		TilesConfigurer configurer = new TilesConfigurer();
		configurer.setDefinitions(new String[] {TILES, VIEWS});
		return configurer;
	}

}

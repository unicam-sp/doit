package it.unicam.doit.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/*
	Un-used
*/

// @Configuration
public class WebConfig implements WebMvcConfigurer {
	
	private final static Logger LOG = LoggerFactory.getLogger(WebConfig.class);
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		LOG.debug("CORS settati da WebMvcConfigurer");
//		registry.addMapping("/**")            
//        .allowedHeaders("Authorization")
//        .allowedOrigins("http://localhost:3000");
	}
}

/*
	Permette tutto
	
	registry.addMapping("/**").allowCredentials(true)               
        .allowedHeaders("Authorization", "Cache-Control", "Content-Type", "Accept", "X-Requested-With", "Access-Control-Allow-Origin", "Access-Control-Allow-Headers", "Origin")
        .exposedHeaders("Access-Control-Expose-Headers", "Authorization", "Cache-Control", "Content-Type", "Access-Control-Allow-Origin", "Access-Control-Allow-Headers", "Origin")
        .allowedMethods("GET", "OPTIONS", "POST", "PUT", "DELETE", "PATCH");
*/

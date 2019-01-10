package jp.co.namihira.townbookweb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import nz.net.ultraq.thymeleaf.LayoutDialect;


@Configuration
public class AppConfig {
		
	@Bean
	public LayoutDialect layoutDialect() {
	    return new LayoutDialect();
	}
 	
}

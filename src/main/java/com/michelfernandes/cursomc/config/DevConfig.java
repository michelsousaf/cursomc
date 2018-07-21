package com.michelfernandes.cursomc.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.michelfernandes.cursomc.services.DBService;

@Configuration
@Profile("test")
public class DevConfig {
	
	@Autowired
	private DBService dbService;
	
	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String strategy;

	
	@Bean
	public boolean istantiateDatabase() throws ParseException {
		
		if(!"create".equals(strategy)) {
			
		}
		dbService.instantiateTestDatabase();
		return true;
	}
}

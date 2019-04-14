package com.osanda.roihunter.fbuserdata;

import org.flywaydb.core.Flyway;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.osanda.roihunter.fbuserdata.util.DirectoryUtil;

@SpringBootApplication
public class FbUserDataApplication {

	public static void main(String[] args) {
		SpringApplication.run(FbUserDataApplication.class, args);
		DirectoryUtil.createDirectories();
	}

	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
	
	@Bean
	public FlywayMigrationStrategy cleanMigrateStrategy() {
	    FlywayMigrationStrategy strategy = new FlywayMigrationStrategy() {
	        @Override
	        public void migrate(Flyway flyway) {
	            flyway.repair();
	            flyway.migrate();
	        }
	    };

	    return strategy;
	}

}

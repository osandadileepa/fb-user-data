package com.osanda.roihunter.fbuserdata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
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

}

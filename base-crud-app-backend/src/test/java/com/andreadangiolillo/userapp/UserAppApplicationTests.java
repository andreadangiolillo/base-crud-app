package com.andreadangiolillo.userapp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest
class UserAppApplicationTests {

	@Value("${spring.datasource.url}")
	private String dbUrl;

	@Test
	void contextLoads() {
		System.out.println(">> DB URL usato nei test: " + dbUrl);
	}

}

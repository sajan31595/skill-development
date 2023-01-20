package com.coaching.skilldevelopment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.coaching.skilldevelopment")
public class SkillDevelopmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(SkillDevelopmentApplication.class, args);
		System.out.println("Welcome to Skill Development Program");
	}
}

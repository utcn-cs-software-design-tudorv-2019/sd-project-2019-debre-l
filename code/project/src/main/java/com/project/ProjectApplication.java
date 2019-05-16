package com.project;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan({"com.controller","com.servicies"})
@EnableJpaRepositories("com.repositories")
@EntityScan("com.entities")
public class ProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectApplication.class, args);
		
		/*
		ApplicationContext ctx = SpringApplication.run(ProjectApplication.class, args);
		
		String[] beanNames = ctx.getBeanDefinitionNames();
        
        Arrays.sort(beanNames);
         
        for (String beanName : beanNames)
        {
            System.out.println(beanName);
        }
        */
	}

}

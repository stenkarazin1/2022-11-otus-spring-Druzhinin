package ru.otus.spring;

import ru.otus.spring.service.TestService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootApplication {

	public static void main( String[] args ) {
        TestService testService = SpringApplication.run( SpringbootApplication.class, args ).getBean( TestService.class );
		testService.start();
	}

}

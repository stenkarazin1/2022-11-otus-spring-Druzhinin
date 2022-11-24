package ru.otus.springboot;

import ru.otus.springboot.service.TestService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootApplication {

	public static void main(String[] args) {
        TestService testService = SpringApplication.run(SpringbootApplication.class, args).getBean( TestService.class );
		//AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext( Main.class );
		//TestService testService = context.getBean( TestService.class );
		testService.start();

		//context.close();
	}

}

package ru.otus.spring;

import ru.otus.spring.service.TestService;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

//@Configuration
@ComponentScan
public class Main {

    public static void main( String[] args ) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext( Main.class );
        TestService testService = context.getBean( TestService.class );
        testService.start();

        context.close();
    }

}


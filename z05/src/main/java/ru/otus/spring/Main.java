package ru.otus.spring;

import ru.otus.spring.service.CrudService;

import org.h2.tools.Console;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Main {

    public static void main(String[] args) throws Exception {

        ApplicationContext context = SpringApplication.run(Main.class);

        CrudService crudService = context.getBean( CrudService.class );

        crudService.browseAllBookInfo();
        //crudService.changeBookInfo();
        crudService.putBook();
        crudService.returnBook();
        Console.main(args);
        crudService.addBook();
        crudService.removeBook();
        crudService.browseBookInfo();
    }
}

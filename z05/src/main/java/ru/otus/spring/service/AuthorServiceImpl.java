package ru.otus.spring.service;

import org.springframework.stereotype.Service;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final IOService ioService;

    AuthorServiceImpl( IOService ioService ) {
        this.ioService = ioService;
    }

    @Override
    public String inputAuthorName() {
        String authorName = ioService.readStringWithPrompt( "Введите фамилию и инициалы автора:" );

        return authorName;
    }

    @Override
    public void printAuthorName( String authorName ) {
        ioService.outputString( "Автор:\t\t\t" + authorName );
    }

}

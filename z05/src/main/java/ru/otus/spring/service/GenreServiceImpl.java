package ru.otus.spring.service;

import org.springframework.stereotype.Service;

@Service
public class GenreServiceImpl implements GenreService {
    private final IOService ioService;

    GenreServiceImpl( IOService ioService ) {
        this.ioService = ioService;
    }

    @Override
    public String inputGenreName() {
        String genreName = ioService.readStringWithPrompt( "Введите жанр:" );

        return genreName;
    }

    @Override
    public void printGenreName( String genreName ) {
        ioService.outputString( "Жанр:\t\t\t" + genreName );
    }

}

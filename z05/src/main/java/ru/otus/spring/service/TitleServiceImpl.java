package ru.otus.spring.service;

import org.springframework.stereotype.Service;

@Service
public class TitleServiceImpl implements TitleService {
    private final IOService ioService;

    TitleServiceImpl( IOService ioService ) {
        this.ioService = ioService;
    }

    @Override
    public String inputTitle() {
        String title = ioService.readStringWithPrompt( "Введите название книги:" );

        return title;
    }

    @Override
    public void printTitle( String title ) {
        ioService.outputString( "Название:\t\t" + title );
    }

}

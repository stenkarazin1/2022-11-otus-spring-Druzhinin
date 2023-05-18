package ru.otus.spring.service;

import org.springframework.stereotype.Service;

@Service
public class YearServiceImpl implements YearService {
    private final IOService ioService;

    YearServiceImpl( IOService ioService ) {
        this.ioService = ioService;
    }

    @Override
    public int inputYear() {
        int year = ioService.readIntWithPrompt( "Введите год издания книги:" );

        return year;
    }

    @Override
    public void printYear( int year ) {
        ioService.outputString( "Год издания:\t" + year );
    }

}

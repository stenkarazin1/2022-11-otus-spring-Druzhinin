package ru.otus.springboot.service;

import org.springframework.stereotype.Service;

@Service
public class NoCinema {
    private final IOService ioService;
    private final MessageSourceWrapper messages;

    public NoCinema( IOService ioService, MessageSourceWrapper messages ) {
        this.ioService = ioService;
        this.messages = messages;
    }

    public void printNoCinema() {
        ioService.outputString( "\n" );
        ioService.outputString( messages.getMessage( "no-cinema-today" ) );
    }

}

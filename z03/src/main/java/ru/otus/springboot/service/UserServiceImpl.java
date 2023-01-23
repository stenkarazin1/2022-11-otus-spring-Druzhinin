package ru.otus.springboot.service;

import ru.otus.springboot.exceptions.NoCinemaException;

import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final IOService ioService;
    private final MessageSourceWrapper messages;

    public UserServiceImpl( IOService ioService, MessageSourceWrapper messages ) {
        this.ioService = ioService;
        this.messages = messages;
    }

    public String inputUserName() throws NoCinemaException {
        String userName = ioService.readStringWithPrompt( messages.getMessage( "input-username" ) );
        // Яйцо пасхальное обыкновенное :-)
        userName = ( userName.equals( "" ) ? "Христо Стоичков" : userName );
        return userName;
    }

    public void printUserName( String userName ) throws NoCinemaException {
        ioService.outputString( "\n===============" );
        ioService.outputString( messages.getMessage( "print-username" ) + userName );
    }

}

package ru.otus.spring.service;

import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final IOService ioService;

    public UserServiceImpl( IOService ioService ) {
        this.ioService = ioService;
    }

    public String inputUserName() {
        String userName = ioService.readStringWithPrompt( "Введите ФИО:" );
        // Яйцо пасхальное обыкновенное :-)
        userName = (userName.equals("") ? "Христо Стоичков" : userName );
        return userName;
    }

    public void printUserName( String userName ) {
        ioService.outputString( "\n==========\nТестуемый: " + userName );
    }

}

package ru.otus.spring.service;

import ru.otus.spring.exceptions.NoCinemaException;

public interface UserService {

    String inputUserName() throws NoCinemaException;
    void printUserName( String userName ) throws NoCinemaException;

}

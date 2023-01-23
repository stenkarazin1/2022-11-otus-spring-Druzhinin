package ru.otus.springboot.service;

import ru.otus.springboot.exceptions.NoCinemaException;

public interface UserService {

    String inputUserName() throws NoCinemaException;
    void printUserName( String userName ) throws NoCinemaException;

}

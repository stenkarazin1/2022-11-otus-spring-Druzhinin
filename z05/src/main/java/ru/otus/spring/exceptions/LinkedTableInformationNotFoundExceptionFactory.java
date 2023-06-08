package ru.otus.spring.exceptions;

public class LinkedTableInformationNotFoundExceptionFactory {

    public LinkedTableInformationNotFoundException getLinkedTableInformationNotFoundException( LinkedTableInformationNotFoundExceptionTypes type ) {
        /* Для целей реализации паттерна Factory LinkedTableInformationNotFoundException следовало бы объявить не классом, а интерфейсом
           Однако это нельзя сделать, т.к. система исключений в Java основана на наследовании
        */
        LinkedTableInformationNotFoundException toReturn = null;
        switch ( type ) {
            case AUTHOR:
                toReturn = new AuthorNotFoundException( "AUTHOR NOT FOUND" );
                break;
            case GENRE:
                toReturn =  new GenreNotFoundException( "GENRE NOT FOUND" );
                break;
            default:
                throw new LinkedTableInformationNotFoundException( "Wrong library exception type: " + type );
        }
        return toReturn;
    }

}

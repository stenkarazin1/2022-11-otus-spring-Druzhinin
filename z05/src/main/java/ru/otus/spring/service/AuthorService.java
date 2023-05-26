package ru.otus.spring.service;

public interface AuthorService {

    String inputAuthorName();

    String inputAuthorNameWithDefaultValue( String defaultValue );

    void printAuthorName( String authorName );

}

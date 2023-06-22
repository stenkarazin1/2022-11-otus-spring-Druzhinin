package ru.otus.spring.service;

public interface TitleService {

    String inputTitle();

    String inputTitleWithDefaultValue( String defaultValue );

    void printTitle( String title );

}

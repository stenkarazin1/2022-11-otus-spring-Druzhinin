package ru.otus.spring.service;

public interface YearService {

    int inputYear();

    int inputYearWithDefaultValue( int defaultValue );
    
    void printYear( int year );

}

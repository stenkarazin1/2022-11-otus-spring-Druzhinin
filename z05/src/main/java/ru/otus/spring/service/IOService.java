package ru.otus.spring.service;

public interface IOService {

    void outputString( String s );

    int readInt();

    int readIntWithPrompt( String prompt );

    Integer readIntWithPromptUncheckedInput( String prompt );

    String readStringWithPrompt( String prompt );

    String readStringWithPromptUncheckedInput( String prompt );

}

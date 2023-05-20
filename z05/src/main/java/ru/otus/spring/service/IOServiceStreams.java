package ru.otus.spring.service;

import org.springframework.stereotype.Service;
import java.io.PrintStream;
import java.util.Scanner;

@Service
public class IOServiceStreams implements IOService {
    private final PrintStream output;
    private final Scanner input;

    public IOServiceStreams() {
        output = System.out;
        input = new Scanner( System.in );
    }

    @Override
    public void outputString( String s ) {
        output.println( s );
    }

    @Override
    public int readInt() {
        return Integer.parseInt( input.nextLine() );
    }

    @Override
    public int readIntWithPrompt( String prompt ) {
        String inp;
        do {
            outputString( prompt );
            inp = input.nextLine();
        } while ( inp.equals( "" ) );
        return Integer.parseInt( inp );
    }

    @Override
    public Integer readIntWithPromptUncheckedInput( String prompt ) {
        outputString( prompt );
        String inp = input.nextLine();
        return !inp.equals( "" ) ? Integer.parseInt( inp ) : null;
    }

    @Override
    public String readStringWithPrompt( String prompt ) {
        String inp;
        do {
            outputString( prompt );
            inp = input.nextLine();
        } while ( inp.equals( "" ) );
        return inp;
    }

    @Override
    public String readStringWithPromptUncheckedInput( String prompt ) {
        outputString( prompt );
        String inp = input.nextLine();
        return !inp.equals( "" ) ? inp : null;
    }

}

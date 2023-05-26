package ru.otus.spring.service;

import org.springframework.stereotype.Service;

@Service
public class YearServiceImpl implements YearService {
    private final IOService ioService;

    YearServiceImpl( IOService ioService ) {
        this.ioService = ioService;
    }

    @Override
    public int inputYear() {
        int year = ioService.readIntWithPrompt( "Введите год издания книги:" );

        return year;
    }

    @Override
    public int inputYearWithDefaultValue( int defaultValue ) {
        printYear( defaultValue );
        Integer year = ioService.readIntWithPromptUncheckedInput( "Введите необходимый год издания книги (если нужно оставить текущий, то просто нажмите Enter):" );

        return year != null ? year : defaultValue;
    }

    @Override
    public void printYear( int year ) {
        ioService.outputString( "Год издания:\t" + year );
    }

}

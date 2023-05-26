package ru.otus.spring.service;

import org.springframework.stereotype.Service;

@Service
public class TitleServiceImpl implements TitleService {
    private final IOService ioService;

    TitleServiceImpl( IOService ioService ) {
        this.ioService = ioService;
    }

    @Override
    public String inputTitle() {
        String title = ioService.readStringWithPrompt( "Введите название книги:" );

        return title;
    }

    @Override
    public String inputTitleWithDefaultValue( String defaultValue ) {
        printTitle( defaultValue );
        String title = ioService.readStringWithPromptUncheckedInput( "Введите необходимое название книги (если нужно оставить текущее, то просто нажмите Enter):" );

        return title != null ? title : defaultValue;
    }

    @Override
    public void printTitle( String title ) {
        ioService.outputString( "Название:\t\t" + title );
    }

}

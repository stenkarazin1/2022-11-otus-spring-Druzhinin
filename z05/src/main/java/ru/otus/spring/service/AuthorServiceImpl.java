package ru.otus.spring.service;

import org.springframework.stereotype.Service;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final IOService ioService;

    AuthorServiceImpl( IOService ioService ) {
        this.ioService = ioService;
    }

    @Override
    public String inputAuthorName() {
        String authorName = ioService.readStringWithPrompt( "Введите фамилию и инициалы автора:" );

        return authorName;
    }

    @Override
    public String inputAuthorNameWithDefaultValue( String defaultValue ) {
        printAuthorName( defaultValue );
        String authorName = ioService.readStringWithPromptUncheckedInput( "Введите необходимые фамилию и инициалы автора книги (если нужно оставить текущие, то просто нажмите Enter):" );

        return authorName != null ? authorName : defaultValue;
    }

    @Override
    public void printAuthorName( String authorName ) {
        ioService.outputString( "Автор:\t\t\t" + authorName );
    }

}

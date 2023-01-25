package ru.otus.springboot.service;

import ru.otus.springboot.config.LocaleHolder;
import ru.otus.springboot.exceptions.NoCinemaException;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class MessageSourceWrapper {
    private final MessageSource messageSource;
    private final LocaleHolder localeHolder;

    public MessageSourceWrapper( MessageSource messageSource, LocaleHolder localeHolder ) {
        this.messageSource = messageSource;
        this.localeHolder = localeHolder;
    }

    public String getMessage( String messageCode ) throws NoCinemaException {
        try {
            return messageSource.getMessage( messageCode, null, localeHolder.getLocale() );
        }
        catch( IllegalArgumentException e ) {
            throw new NoCinemaException( "No such property" );
        }
    }

    public String getEmergencyMessage( String messageCode ) {
        // Причиной исключения может быть локаль
        // Поэтому нужно разомкнуть круг
        try {
            return messageSource.getMessage( messageCode, null, localeHolder.getLocale() );
        }
        catch( IllegalArgumentException e ) {
            return messageSource.getMessage( messageCode, null, Locale.getDefault() );
        }
    }

}

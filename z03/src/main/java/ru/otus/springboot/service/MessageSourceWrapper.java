package ru.otus.springboot.service;

import ru.otus.springboot.config.LocaleHolder;
import ru.otus.springboot.exceptions.NoCinemaException;

import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class MessageSourceWrapper {
    private final ResourceBundleMessageSource messageSource;
    private final LocaleHolder localeHolder;

    public MessageSourceWrapper( LocaleHolder localeHolder ) {
        this.messageSource = new ResourceBundleMessageSource();
        this.messageSource.setBasenames( "messages" );
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

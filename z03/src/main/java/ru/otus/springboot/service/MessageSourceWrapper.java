package ru.otus.springboot.service;

import ru.otus.springboot.config.LocaleHolder;

import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Service;

@Service
public class MessageSourceWrapper {
    private final ResourceBundleMessageSource messageSource;
    private final LocaleHolder localeHolder;

    public MessageSourceWrapper( LocaleHolder localeHolder ) {
        this.messageSource = new ResourceBundleMessageSource();
        this.messageSource.setBasenames( "messages" );
        this.localeHolder = localeHolder;
    }

    public String getMessage( String messageCode ) {
        return messageSource.getMessage( messageCode, null, localeHolder.getLocale() );
    }

}

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

    public String getMessage( String messageCode ) {
        // Если в ресурсах не предусмотрена локализация для заданной в свойствах локали, то для вывода сообщений будет использована дефолтная локаль
        return messageSource.getMessage( messageCode, null, localeHolder.getLocale() );
    }


}

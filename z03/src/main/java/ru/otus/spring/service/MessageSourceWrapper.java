package ru.otus.spring.service;

import ru.otus.spring.config.LocaleHolder;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

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

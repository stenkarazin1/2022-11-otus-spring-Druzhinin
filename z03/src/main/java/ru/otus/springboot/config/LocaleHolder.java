package ru.otus.springboot.config;

import org.springframework.stereotype.Component;
import java.util.Locale;

@Component
public class LocaleHolder {
    private final Locale locale;

    public LocaleHolder( PropertyConfig propertyConfig ) {
        this.locale = new Locale( propertyConfig.getProperty( "locale" ) );
    }

    public Locale getLocale() {
        return locale;
    }

}

package ru.otus.springboot.config;

import org.springframework.stereotype.Component;
import java.util.Locale;

@Component
public class LocaleHolder {
    private final ApplicationProperties ap;
    private final Locale locale;

    public LocaleHolder( ApplicationProperties ap ) {
        this.ap = ap;
        this.locale = new Locale( ap.getLocale() );
    }

    public Locale getLocale() {
        return locale;
    }

}

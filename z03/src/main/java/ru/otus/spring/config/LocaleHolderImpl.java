package ru.otus.spring.config;

import org.springframework.stereotype.Component;
import java.util.Locale;

@Component
public class LocaleHolderImpl implements LocaleHolder {
    private final ApplicationProperties ap;
    private String localeName;
    private Locale locale;

    public LocaleHolderImpl( ApplicationProperties ap ) {
        this.ap = ap;
    }

    private void setLocale() {
        this.localeName = ap.getLocaleName();
        this.locale = new Locale( localeName );
    }

    public String getLocaleName() {
        if( localeName == null ) {
            setLocale();
        }
        return localeName;
    }

    public Locale getLocale() {
        if( locale == null ) {
            setLocale();
        }
        return locale;
    }

}

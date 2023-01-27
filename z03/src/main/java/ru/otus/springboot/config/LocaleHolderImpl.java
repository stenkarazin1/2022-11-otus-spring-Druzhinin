package ru.otus.springboot.config;

import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class LocaleHolderImpl implements LocaleHolder {
    private final PropertyConfig propertyConfig;
    private String localeName;
    private Locale locale;

    public LocaleHolderImpl( PropertyConfig propertyConfig ) {
        this.propertyConfig = propertyConfig;
    }

    private void setLocale() throws IllegalArgumentException {
        this.localeName = propertyConfig.getProperty( "locale-name" );
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

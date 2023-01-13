package ru.otus.springboot.config;

import org.springframework.stereotype.Component;
import java.util.Locale;

@Component
public class LocaleHolder {
    private String localeName;
    private Locale locale;

    public LocaleHolder( PropertyConfig propertyConfig ) {
        try {
            this.localeName = propertyConfig.getProperty( "locale-name" );
            this.locale = new Locale( localeName );
        }
        catch( IllegalArgumentException e ) {
            // Поле locale не инициализировано, однако данное обстоятельство не препятствует корректному выполнению программы
            // Такое происходит, когда в качестве аргумента в метод getProperty() передается строка, не совпадающая ни c одним из названий свойств в файле application.yml
        }
    }

    public String getLocaleName() {
        return localeName;
    }

    public Locale getLocale() {
        return locale;
    }

}

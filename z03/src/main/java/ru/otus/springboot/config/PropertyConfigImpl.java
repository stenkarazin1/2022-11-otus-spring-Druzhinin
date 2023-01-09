package ru.otus.springboot.config;
// Возможно, имеет смысл объединить класс PropertyConfig с классом ApplicationProperties, т.к. возникает лишний слой
import org.springframework.stereotype.Component;

@Component
public class PropertyConfigImpl implements PropertyConfig {
    private final ApplicationProperties ap;
    private String fileName;
    private String requiredTestItemListSize;
    private String locale;

    public PropertyConfigImpl( ApplicationProperties ap ) {
        this.ap = ap;
        this.fileName = ap.getConfigFile();
        this.requiredTestItemListSize = ap.getNumItems();
        this.locale = ap.getLocale();
    }

    private String getFileName() {
        return fileName;
    }

    private String getRequiredTestItemListSize() {
        return requiredTestItemListSize;
    }

    private String getLocale() {
        return locale;
    }

    public String getProperty( String property ) {
        // Пользователю класса PropertyConfig предоставляется данный унифицированный метод для обращения к файлу свойств application.yml
        // Достоинство выбранного подхода состоит в том, что пользователь класса PropertyConfig обращается к свойству по имени, передавая это имя данному унифицированному методу
        // С другой стороны, на пользователя класса PropertyConfig перекладывается задача интерпретации свойства, т.е. приведения полученного значения к нужному типу данных
        if( property.equals( "config-file" ) ) return getFileName();
        if( property.equals( "num-items" ) ) return getRequiredTestItemListSize();
        if( property.equals( "locale" ) ) return getLocale();
        //throw new IllegalArgumentException( "" );
        return null;
    }

}

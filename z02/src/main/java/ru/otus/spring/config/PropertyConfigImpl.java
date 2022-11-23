package ru.otus.spring.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:application.properties")
public class PropertyConfigImpl implements PropertyConfig {
    private String fileName;
    private Integer requiredTestItemListSize;
    private String locale;

    public PropertyConfigImpl( @Value("${configfile}") String fileName,
                               @Value("${numitems}") Integer requiredTestItemListSize,
                               @Value("${locale}") String locale
                             ) {
        this.fileName = fileName;
        this.requiredTestItemListSize = requiredTestItemListSize;
        this.locale = locale;
    }

    public String getFileName() {
        return fileName;
    }

    public Integer getRequiredTestItemListSize() {
        return requiredTestItemListSize;
    }

    public String getLocale() {
        return locale;
    }

}

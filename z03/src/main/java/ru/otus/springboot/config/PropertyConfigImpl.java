package ru.otus.springboot.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

//@ConfigurationProperties("application")
@Component
//@PropertySource("classpath:application.properties")
public class PropertyConfigImpl implements PropertyConfig {
    private final ApplicationProperties ap;
    private String fileName;
    private Integer requiredTestItemListSize;
    private String locale;

    public PropertyConfigImpl( ApplicationProperties ap ) {
        this.ap = ap;
        this.fileName = ap.getConfigFile();
        this.requiredTestItemListSize = ap.getNumItems();
        this.locale = ap.getLocale();
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

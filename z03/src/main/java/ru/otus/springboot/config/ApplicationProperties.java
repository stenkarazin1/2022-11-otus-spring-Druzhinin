package ru.otus.springboot.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties( "application" )
@Component
public class ApplicationProperties {
    private String configFile;
    private String numItems;
    private String locale;

    public void setConfigFile(String configFile) {
        this.configFile = configFile;
    }

    public String getConfigFile() {
        return configFile;
    }

    public void setNumItems(String numItems) {
        this.numItems = numItems;
    }

    public String getNumItems() {
        return numItems;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getLocale() {
        return locale;
    }
}

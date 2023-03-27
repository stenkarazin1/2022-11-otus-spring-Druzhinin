package ru.otus.spring.config;

import org.springframework.stereotype.Component;

@Component
public class FileNameProviderImpl implements FileNameProvider {
    private final ApplicationProperties ap;

    public FileNameProviderImpl( ApplicationProperties ap ) {
        this.ap = ap;
    }

    public String getFileName() {
        return ap.getConfigFile();
    }
}

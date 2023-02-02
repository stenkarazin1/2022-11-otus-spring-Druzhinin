package ru.otus.springboot.config;

import org.springframework.stereotype.Component;

@Component
public class FileNameProviderImpl implements FileNameProvider {
    private final ApplicationProperties ap;

    public FileNameProviderImpl( ApplicationProperties ap ) {
        this.ap = ap;
    }

    public String getFileName() {
        String fileName = ap.getConfigFile();
        return fileName;
    }
}

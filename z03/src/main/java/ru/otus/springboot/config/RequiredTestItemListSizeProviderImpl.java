package ru.otus.springboot.config;

import org.springframework.stereotype.Component;

@Component
public class RequiredTestItemListSizeProviderImpl implements RequiredTestItemListSizeProvider {
    private final ApplicationProperties ap;

    public RequiredTestItemListSizeProviderImpl( ApplicationProperties ap ) {
        this.ap = ap;
    }

    public int getRequiredTestItemListSize() throws NumberFormatException {
        return Integer.parseInt( ap.getNumItems() );
    }
}

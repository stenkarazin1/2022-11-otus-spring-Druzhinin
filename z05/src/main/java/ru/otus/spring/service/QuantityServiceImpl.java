package ru.otus.spring.service;

import org.springframework.stereotype.Service;

@Service
public class QuantityServiceImpl implements QuantityService {
    private final IOService ioService;

    QuantityServiceImpl( IOService ioService ) {
        this.ioService = ioService;
    }

    @Override
    public int inputQuantity() {
        int quantity = ioService.readIntWithPrompt( "Введите доступное количество экземпляров книги:" );

        return quantity;
    }

    @Override
    public void printQuantity( int quantity ) {
        ioService.outputString( "Доступно:\t\t" + quantity );
    }

}

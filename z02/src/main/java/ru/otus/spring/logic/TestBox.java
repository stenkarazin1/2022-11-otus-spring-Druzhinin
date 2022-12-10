package ru.otus.spring.logic;

import ru.otus.spring.domain.TestItem;

import java.util.List;

public interface TestBox {

    List< TestItem > getTestItemList( Integer requiredTestItemListSize );

}

package ru.otus.springboot.logic;

import ru.otus.springboot.exceptions.NoCinemaException;
import ru.otus.springboot.domain.TestItem;

import java.util.List;

public interface TestBox {

    List< TestItem > getTestItemList()  throws NoCinemaException;

}

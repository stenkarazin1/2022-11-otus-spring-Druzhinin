package ru.otus.spring.logic;

import ru.otus.spring.exceptions.NoCinemaException;
import ru.otus.spring.domain.TestItem;

import java.util.List;

public interface TestBox {
    // Класс TestBox реализует промежуточный слой - слой бизнес-логики
    // Его задача заключается в формировании списка вопросов заданного объема, комбинируя и перемешивая вопросы из разных источников

    List< TestItem > getTestItemList()  throws NoCinemaException;

}

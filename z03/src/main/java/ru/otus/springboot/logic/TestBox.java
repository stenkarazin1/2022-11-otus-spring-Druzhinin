package ru.otus.springboot.logic;

import ru.otus.springboot.exceptions.NoCinemaException;
import ru.otus.springboot.domain.TestItem;

import java.util.List;

public interface TestBox {
    // Класс TestBox реализует промежуточный слой - слой бизнес-логики
    // Его задача заключается в формировании списка вопросов заданного объема, комбинируя и перемешивая вопросы из разных источников

    List< TestItem > getTestItemList()  throws NoCinemaException;

}

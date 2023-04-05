package ru.otus.spring.dao;

import ru.otus.spring.exceptions.NoCinemaException;
import ru.otus.spring.domain.TestItem;

import java.util.List;

public interface TestBoxDao {

    List<TestItem> getTestItemList() throws NoCinemaException;

}

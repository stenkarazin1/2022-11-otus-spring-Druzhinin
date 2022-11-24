package ru.otus.springboot.logic;

import ru.otus.springboot.dao.TestBoxDao;
import ru.otus.springboot.domain.TestItem;

import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class TestBoxImpl implements TestBox {
    // Класс TestBox реализует промежуточный слой - слой бизнес-логики
    // Его задача заключается в формировании списка вопросов заданного объема, комбинируя и перемешивая вопросы из разных источников

    private final TestBoxDao testBoxDao;

    public TestBoxImpl( TestBoxDao dao ) {
        this.testBoxDao = dao;
    }

    public List< TestItem > getTestItemList( Integer requiredTestItemListSize ) {
        final List<TestItem> testItemList = testBoxDao.getTestItemList();
        final Integer testItemListSize = testItemList.size();
        if( testItemListSize != requiredTestItemListSize ) {
            // Какие-то действия
            // Можно также реализовать с помощью исключений
        }

        Collections.shuffle( testItemList );
        for( int i = requiredTestItemListSize; i < testItemListSize; i++ ) {
            testItemList.remove( i );
        }
        return testItemList;
    }

}

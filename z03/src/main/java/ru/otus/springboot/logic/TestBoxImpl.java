package ru.otus.springboot.logic;

import ru.otus.springboot.exceptions.NoCinemaException;
import ru.otus.springboot.dao.TestBoxDao;
import ru.otus.springboot.domain.TestItem;

import org.springframework.stereotype.Component;
import java.io.IOException;
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

    public List< TestItem > getTestItemList( Integer requiredTestItemListSize ) throws NoCinemaException {
        final List< TestItem > testItemList;
        try {
            testItemList = testBoxDao.getTestItemList();
        }
        catch ( IOException e ) {
            throw new NoCinemaException( "Incorrect file input" );
        }
        catch ( NullPointerException e ) {
            throw new NoCinemaException("File with questions is absent");
        }

        final int testItemListSize = testItemList.size();
        if( testItemListSize < requiredTestItemListSize ) {
            throw new NoCinemaException( "Too less test items" );
        }

        Collections.shuffle( testItemList );
        for( int i = requiredTestItemListSize; i < testItemListSize; i++ ) {
            testItemList.remove( i );
        }
        return testItemList;
    }

}

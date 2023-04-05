package ru.otus.spring.logic;

import ru.otus.spring.config.RequiredTestItemListSizeProvider;
import ru.otus.spring.exceptions.NoCinemaException;
import ru.otus.spring.dao.TestBoxDao;
import ru.otus.spring.domain.TestItem;

import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class TestBoxImpl implements TestBox {
    private final RequiredTestItemListSizeProvider requiredTestItemListSizeProvider;
    private final TestBoxDao testBoxDao;

    public TestBoxImpl( RequiredTestItemListSizeProvider requiredTestItemListSizeProvider, TestBoxDao dao ) {
        this.requiredTestItemListSizeProvider = requiredTestItemListSizeProvider;
        this.testBoxDao = dao;
    }

    public List< TestItem > getTestItemList() throws NoCinemaException {
        final int requiredTestItemListSize;
        try {
            requiredTestItemListSize = requiredTestItemListSizeProvider.getRequiredTestItemListSize();
        }
        catch ( NumberFormatException e ) {
            throw new NoCinemaException( "Invalid property value" );
        }

        final List< TestItem > testItemList = testBoxDao.getTestItemList();
        final int testItemListSize = testItemList.size();
        if( testItemListSize < requiredTestItemListSize ) {
            throw new NoCinemaException( "Too less test items" );
        }

        Collections.shuffle( testItemList );
        for( int i = requiredTestItemListSize; i < testItemListSize; i++ ) {
            testItemList.remove( testItemList.size() - 1 );
        }
        return testItemList;
    }

}

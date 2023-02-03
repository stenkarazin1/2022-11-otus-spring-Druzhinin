package ru.otus.springboot.logic;

import ru.otus.springboot.config.PropertyConfig;
import ru.otus.springboot.exceptions.NoCinemaException;
import ru.otus.springboot.dao.TestBoxDao;
import ru.otus.springboot.domain.TestItem;

import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class TestBoxImpl implements TestBox {
    private final PropertyConfig propertyConfig;
    private final TestBoxDao testBoxDao;

    public TestBoxImpl( PropertyConfig propertyConfig, TestBoxDao dao ) {
        this.propertyConfig = propertyConfig;
        this.testBoxDao = dao;
    }

    public List< TestItem > getTestItemList() throws NoCinemaException {
        Integer requiredTestItemListSize;
        try {
            requiredTestItemListSize = Integer.valueOf( propertyConfig.getProperty( "num-items" ) );
        }
        catch( IllegalArgumentException e ) {
            throw new NoCinemaException( "No such property" );
        }

        final List< TestItem > testItemList = testBoxDao.getTestItemList();

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

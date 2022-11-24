package ru.otus.springboot.service;

import ru.otus.springboot.config.PropertyConfig;
import ru.otus.springboot.domain.AnsweredTestItem;
import ru.otus.springboot.domain.TestItem;
import ru.otus.springboot.logic.TestBox;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestServiceImpl implements TestService {
    private final TestBox testBox;
    private final UserService userService;
    private final AnswersService answersService;
    private final Integer requiredTestItemListSize;

    public TestServiceImpl( PropertyConfig propertyConfig, TestBox testBox, UserService userService, AnswersService answersService ) {
        this.testBox = testBox;
        this.userService = userService;
        this.answersService = answersService;
        this.requiredTestItemListSize = propertyConfig.getRequiredTestItemListSize();
    }

    public void start() {
        String userName = userService.inputUserName();

        List< TestItem > testItemList = testBox.getTestItemList( requiredTestItemListSize );
        if( testItemList.size() != requiredTestItemListSize ) {
            // Какие-то действия
            // Можно также реализовать с помощью исключений
        }
        List< AnsweredTestItem > answeredTestItemList = answersService.inputAnswers( testItemList );

        userService.printUserName( userName );
        answersService.printResults( answeredTestItemList );
    }

}

package ru.otus.springboot.service;

import ru.otus.springboot.exceptions.NoCinemaException;
import ru.otus.springboot.domain.AnsweredTestItem;
import ru.otus.springboot.domain.TestItem;
import ru.otus.springboot.logic.TestBox;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TestServiceImpl implements TestService {
    private final TestBox testBox;
    private final NoCinema noCinema;
    private final UserService userService;
    private final AnswersService answersService;

    public TestServiceImpl( TestBox testBox, NoCinema noCinema, UserService userService, AnswersService answersService ) {
        this.testBox = testBox;
        this.noCinema = noCinema;
        this.userService = userService;
        this.answersService = answersService;
    }

    public void start() {
        List< TestItem > testItemList;
        try {
            testItemList = testBox.getTestItemList();
        }
        catch ( NoCinemaException e ) {
            noCinema.printNoCinema();
            return;
        }

        String userName = userService.inputUserName();
        List< AnsweredTestItem > answeredTestItemList = answersService.inputAnswers( testItemList );

        userService.printUserName( userName );
        answersService.printResults( answeredTestItemList );
        answersService.printResultsWidely( answeredTestItemList );
    }

}

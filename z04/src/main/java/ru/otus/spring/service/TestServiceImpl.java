package ru.otus.spring.service;

import ru.otus.spring.exceptions.NoCinemaException;
import ru.otus.spring.domain.AnsweredTestItem;
import ru.otus.spring.domain.TestItem;
import ru.otus.spring.logic.TestBox;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import java.util.List;

@ShellComponent
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

    @ShellMethod( value = "Starting quiz", key = { "s" } )
    public void start() {
        List< TestItem > testItemList;
        try {
            testItemList = testBox.getTestItemList();

            String userName = userService.inputUserName();
            List< AnsweredTestItem > answeredTestItemList = answersService.inputAnswers( testItemList );

            userService.printUserName( userName );
            answersService.printResults( answeredTestItemList );
            answersService.printResultsWidely( answeredTestItemList );
        }
        catch ( NoCinemaException e ) {
            noCinema.printNoCinema();
            return;
        }
    }

}

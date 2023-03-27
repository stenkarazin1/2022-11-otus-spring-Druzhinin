package ru.otus.spring.service;

import ru.otus.spring.domain.AnsweredTestItem;
import ru.otus.spring.domain.TestItem;
import ru.otus.spring.exceptions.NoCinemaException;

import java.util.List;

public interface AnswersService {

    List< AnsweredTestItem > inputAnswers( List< TestItem > testItemList ) throws NoCinemaException;
    void printResults( List< AnsweredTestItem > answeredTestItemList ) throws NoCinemaException;
    void printResultsWidely( List< AnsweredTestItem > answeredTestItemList ) throws NoCinemaException;

}

package ru.otus.springboot.service;

import ru.otus.springboot.domain.AnsweredTestItem;
import ru.otus.springboot.domain.TestItem;
import ru.otus.springboot.exceptions.NoCinemaException;

import java.util.List;

public interface AnswersService {

    List< AnsweredTestItem > inputAnswers( List< TestItem > testItemList ) throws NoCinemaException;
    void printResults( List< AnsweredTestItem > answeredTestItemList ) throws NoCinemaException;
    void printResultsWidely( List< AnsweredTestItem > answeredTestItemList ) throws NoCinemaException;

}

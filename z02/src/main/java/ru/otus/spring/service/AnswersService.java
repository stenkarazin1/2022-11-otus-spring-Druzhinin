package ru.otus.spring.service;

import ru.otus.spring.domain.AnsweredTestItem;
import ru.otus.spring.domain.TestItem;

import java.util.List;

public interface AnswersService {

    List< AnsweredTestItem > inputAnswers( List< TestItem > testItemList );
    void printResults( List< AnsweredTestItem > answeredTestItemList );

}

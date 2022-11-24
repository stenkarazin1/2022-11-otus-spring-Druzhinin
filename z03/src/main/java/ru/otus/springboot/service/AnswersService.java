package ru.otus.springboot.service;

import ru.otus.springboot.domain.AnsweredTestItem;
import ru.otus.springboot.domain.TestItem;

import java.util.List;

public interface AnswersService {

    List< AnsweredTestItem > inputAnswers( List< TestItem > testItemList );
    void printResults( List< AnsweredTestItem > answeredTestItemList );

}

package ru.otus.spring.service;

import ru.otus.spring.domain.Variant;
import ru.otus.spring.domain.TestItem;
import ru.otus.spring.domain.AnsweredTestItem;
import ru.otus.spring.exceptions.NoCinemaException;

import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class AnswersServiceImpl implements AnswersService {
    private final IOService ioService;
    private final MessageSourceWrapper messages;

    public AnswersServiceImpl( IOService ioService, MessageSourceWrapper messages ) {
        this.ioService = ioService;
        this.messages = messages;
    }

    public List< AnsweredTestItem > inputAnswers( List< TestItem > testItemList ) throws NoCinemaException {
        int testItemListSize = testItemList.size();
        // Список вопросов, на которые ответили
        List< AnsweredTestItem > answeredTestItemList = new LinkedList<AnsweredTestItem>();

        TestItem testItem;
        for ( int j = 0; j < testItemListSize; j++ ) {
            testItem = testItemList.get( j );
            ioService.outputString( "---------------" );
            ioService.outputString( testItem.getQuestion() );
            List<Variant> variantList = testItem.getVariantList();
            int variantListSize = variantList.size();
            for ( int i = 0; i < variantListSize; i++ ) {
                ioService.outputString( "   " + ( i + 1 ) + ") " + variantList.get( i ).getVariantText() );
            }

            // Проверка ввода не реализована. Укажите один из предложенных вариантов ответа
            int response = ioService.readIntWithPrompt( messages.getMessage( "input-number-corresponding-your-choice" ) );
            AnsweredTestItem answeredTestItem = new AnsweredTestItem( testItem, response );
            answeredTestItemList.add( answeredTestItem );
        }
        return answeredTestItemList;
    }

    public void printResults( List< AnsweredTestItem > answeredTestItemList ) throws NoCinemaException {
        int answeredTestItemListSize = answeredTestItemList.size();
        int rightAnsweredTestItemCount = 0;
        for (AnsweredTestItem answeredTestItem : answeredTestItemList) {
            if (answeredTestItem
                    .getTestItem()
                    .getVariant(answeredTestItem.getUserAnswer())
                    .getIsRight()) {
                rightAnsweredTestItemCount++;
            }
        }
        ioService.outputString("===============");
        ioService.outputString(messages.getMessage("result") + rightAnsweredTestItemCount + " / " + answeredTestItemListSize);
    }

    public void printResultsWidely( List< AnsweredTestItem > answeredTestItemList ) throws NoCinemaException {
        int answeredTestItemListSize = answeredTestItemList.size();
        for( int i = 0; i < answeredTestItemListSize; i++ ) {
            AnsweredTestItem answeredTestItem = answeredTestItemList.get( i );
            ioService.outputString( "---------------\n" + answeredTestItem.getQuestion() );
            ioService.outputString( messages.getMessage( "your-answer" ) + answeredTestItem.getVariant( answeredTestItem.getUserAnswer() ) );
            ioService.outputString( messages.getMessage( "right-answer" ) + answeredTestItem.getTestItem().getRightAnswer() );
        }
    }

}

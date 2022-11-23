package ru.otus.spring.service;

import ru.otus.spring.domain.Variant;
import ru.otus.spring.domain.TestItem;
import ru.otus.spring.domain.AnsweredTestItem;

import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class AnswersServiceImpl implements AnswersService {
    private final IOService ioService;

    public AnswersServiceImpl( IOService ioService ) {
        this.ioService = ioService;
    }

    public List< AnsweredTestItem > inputAnswers( List< TestItem > testItemList ) {
        Integer testItemListSize = testItemList.size();
        // Список вопросов, на которые ответили
        List< AnsweredTestItem > answeredTestItemList = new LinkedList<AnsweredTestItem>();

        TestItem testItem;
        for ( int j = 0; j < testItemListSize; j++ ) {
            testItem = testItemList.get( j );
            ioService.outputString( "\n----------" );
            ioService.outputString(testItem.getQuestion());
            List<Variant> variantList = testItem.getVariantList();
            int i = 0;
            for ( Variant variant : variantList ) {
                i++;
                ioService.outputString( "   " + i + ") " + variant.getVariantText() );
            }

            // Проверка ввода не реализована. Укажите один из предложенных вариантов ответа
            int response = ioService.readIntWithPrompt( "Введите номер, соответствующий выбранному Вами ответу: " );
            AnsweredTestItem answeredTestItem = new AnsweredTestItem( testItem, response );
            answeredTestItemList.add( answeredTestItem );
        }
        return answeredTestItemList;
    }

    public void printResults( List< AnsweredTestItem > answeredTestItemList ) {
        Integer answeredTestItemListSize = answeredTestItemList.size();
        Integer rightAnsweredTestItemCount = 0;
        for( AnsweredTestItem answeredTestItem : answeredTestItemList ) {
            if( answeredTestItem
                    .getTestItem()
                    .getVariant( answeredTestItem.getUserAnswer() )
                    .getIsRight() ) {
                rightAnsweredTestItemCount++;
            }
        }
        ioService.outputString(  "Результат: Верно " + rightAnsweredTestItemCount + " из " + answeredTestItemListSize );

        // Оставшуюся часть метода - перечисление -  можно реализовать как отдельный метод
        for( int i = 0; i < answeredTestItemListSize; i++ ) {
            AnsweredTestItem answeredTestItem = answeredTestItemList.get( i );
            ioService.outputString( "\n----------\n" + answeredTestItem.getQuestion() );
            ioService.outputString( "   Вы ответили:      " + answeredTestItem.getVariant( answeredTestItem.getUserAnswer() ) );
            ioService.outputString( "   Правильный ответ: " + answeredTestItem.getTestItem().getRightAnswer() );
        }
    }

}

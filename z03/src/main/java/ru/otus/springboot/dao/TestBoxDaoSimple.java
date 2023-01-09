package ru.otus.springboot.dao;

import ru.otus.springboot.config.PropertyConfig;
import ru.otus.springboot.domain.TestItem;
import ru.otus.springboot.domain.Variant;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Component
public class TestBoxDaoSimple implements TestBoxDao {
    private final String fileName;
    private final String locale;

    public TestBoxDaoSimple( PropertyConfig propertyConfig ) {
        fileName = propertyConfig.getFileName();
        locale = propertyConfig.getLocale();
    }

    public List< TestItem > getTestItemList() throws IOException {
        List< TestItem > testItemList = new ArrayList<>();

        InputStream inputStream = null;
        inputStream = getClass().getResourceAsStream( fileName );

        try ( BufferedReader reader = new BufferedReader( new InputStreamReader( inputStream ) ) ) {
            String line;
            outer:
            while ( (line = reader.readLine()) != null ) {
                // Вопросы хранятся в файле формата .csv, т.е. данные отделены запятыми
                // Одна строка соответствует одному вопросу на одном языке
                // В каждой строке сначала записан код языка (en, ru и т.д.), затем вопрос, а затем варианты ответа, один из которых правильный
                // Например: ru,Вопрос,Вариант1,,Вариант2,Вариант3
                // Правильный вариант предваряется двумя запятыми, т.е. в примере выше правильным является Вариант2
                if( line.isEmpty() || !line.contains(",,") ) {
                    // Bad format
                    continue;
                }
                String[] str = line.split( "," );
                if( str.length < 4 || str[0].isEmpty() || str[1].isEmpty() ) {
                    // Bad format
                    continue;
                }
                if( !locale.equals( str[0] ) ) {
                    // Another language
                    continue;
                }
                List< Variant > variantList = new ArrayList<>();
                boolean isRight = false;
                String question = str[1];
                for( int i = 2; i < str.length; i++ ) {
                    if( str[i].isEmpty() ) {
                        if( !isRight ) {
                            isRight = true;
                        }
                        else {
                            // Bad format
                            continue outer;
                        }
                    }
                    else {
                        Variant variant = new Variant( str[i], isRight );
                        variantList.add( variant );
                        isRight = false;
                    }
                }
                TestItem testItem = new TestItem( question, variantList );
                testItemList.add( testItem );
            }
            return testItemList;
        }
    }

}

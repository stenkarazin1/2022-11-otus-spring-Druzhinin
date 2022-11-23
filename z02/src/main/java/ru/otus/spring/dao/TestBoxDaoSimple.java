package ru.otus.spring.dao;

import ru.otus.spring.config.PropertyConfig;

import org.springframework.stereotype.Component;
import ru.otus.spring.domain.TestItem;
import ru.otus.spring.domain.Variant;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@Component
public class TestBoxDaoSimple implements TestBoxDao {
    private final String fileName;
    private final String locale;

    public TestBoxDaoSimple( PropertyConfig propertyConfig ) {
        fileName = propertyConfig.getFileName();
        locale = propertyConfig.getLocale();
    }

    private String getTextTranslation( String json, String locale ) {
        // Заготовка для следующего задания
        return json;
    }


    public List< TestItem > getTestItemList() {
        List< TestItem > testItemList = new ArrayList<>();

        InputStream inputStream = null;
        inputStream = getClass().getResourceAsStream( fileName );

        if( inputStream != null ) {
            String line;
            try ( BufferedReader reader = new BufferedReader( new InputStreamReader( inputStream ) ) ) {
                outer:
                while ( (line = reader.readLine()) != null ) {
                    // Вопросы хранятся в файле формата .csv, т.е. данные отделены запятыми
                    // Одна строка соответствует одному вопросу
                    // В каждой строке сначала записан вопрос, а затем варианты ответа, один из которых правильный
                    // Например: Вопрос,Вариант1,,Вариант2,Вариант3
                    // Правильный вариант предваряется двумя запятыми, т.е. в примере выше правильным является Вариант2
                    if( line.isEmpty() || !line.contains(",,")) {
                        // Bad format
                        continue;
                    }
                    String[] str = line.split( "," );
                    if( str.length < 3 || str[0].isEmpty() ) {
                        // Bad format
                        continue;
                    }
                    List< Variant > variantList = new ArrayList<>();
                    Boolean isRight = false;
                    String question = getTextTranslation( str[0], locale );
                    for( int i = 1; i < str.length; i++ ) {
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
            } catch( IOException e ) {
                // Можно обернуть исключение и бросить выше
            }
        }
        return testItemList;
    }

}

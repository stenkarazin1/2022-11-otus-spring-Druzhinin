package ru.otus.springboot.dao;

import ru.otus.springboot.config.FileNameProvider;
import ru.otus.springboot.config.LocaleHolder;
import ru.otus.springboot.exceptions.NoCinemaException;
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
public class TestBoxDaoLocalizedCsv implements TestBoxDao {
    private final FileNameProvider fileNameProvider;
    private final LocaleHolder localeHolder;

    public TestBoxDaoLocalizedCsv( FileNameProvider fileNameProvider, LocaleHolder localeHolder ) {
        this.localeHolder = localeHolder;
        this.fileNameProvider = fileNameProvider;
    }

    public List< TestItem > getTestItemList() throws NoCinemaException {
        String fileName = fileNameProvider.getFileName();
        String localeName = localeHolder.getLocaleName();

        List< TestItem > testItemList = new ArrayList<>();

        InputStream inputStream = null;
        inputStream = getClass().getResourceAsStream(fileName);

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
                if( !localeName.equals( str[0] ) ) {
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
        catch ( IOException e ) {
            throw new NoCinemaException( "Incorrect file input" );
        }
        catch ( NullPointerException e ) {
            throw new NoCinemaException( "File with questions not found" );
        }
    }

}

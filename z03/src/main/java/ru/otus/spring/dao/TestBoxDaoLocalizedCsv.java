package ru.otus.spring.dao;

import ru.otus.spring.config.FileNameProvider;
import ru.otus.spring.config.LocaleHolder;
import ru.otus.spring.exceptions.NoCinemaException;
import ru.otus.spring.domain.TestItem;
import ru.otus.spring.domain.Variant;

import org.springframework.stereotype.Component;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class TestBoxDaoLocalizedCsv implements TestBoxDao {
    private final FileNameProvider fileNameProvider;
    private final LocaleHolder localeHolder;

    public TestBoxDaoLocalizedCsv( FileNameProvider fileNameProvider, LocaleHolder localeHolder ) {
        this.localeHolder = localeHolder;
        this.fileNameProvider = fileNameProvider;
    }

    private boolean isRightFormat( String str ) {
        return !str.isEmpty() && str.contains(",,") && !str.contains(",,,");
    }

    private boolean isRightFormat2( LinkedList< String > list ) {
        return list.size() > 4 && !list.getFirst().isEmpty() && !list.get( 1 ).isEmpty() && !list.get( list.size() - 1 ).isEmpty();
    }


    private Variant getVariant( Iterator< String > iterator ) {
        boolean isRight = false;
        String str = iterator.next();
        iterator.remove();
        if ( str.isEmpty() ) {
            isRight = true;
            str = iterator.next();
            iterator.remove();
        }
        return new Variant( str, isRight );
    }

    public List< TestItem > getTestItemList() throws NoCinemaException {
        final String fileName = fileNameProvider.getFileName();
        final String localeName = localeHolder.getLocaleName();

        List< TestItem > testItemList = new ArrayList<>();

        InputStream inputStream = null;
        inputStream = getClass().getResourceAsStream(fileName);

        try ( BufferedReader reader = new BufferedReader( new InputStreamReader( inputStream ) ) ) {
            String line;
            while ( (line = reader.readLine()) != null ) {
                // Вопросы хранятся в файле формата .csv, т.е. данные отделены запятыми
                // Одна строка соответствует одному вопросу на одном языке
                // В каждой строке сначала записан код языка (en, ru и т.д.), затем вопрос, а затем варианты ответа, один из которых правильный
                // Например: ru,Вопрос,Вариант1,,Вариант2,Вариант3
                // Правильный вариант предваряется двумя запятыми, т.е. в примере выше правильным является Вариант2
                if( !isRightFormat( line ) ) {
                    // Bad format
                    continue;
                }

                LinkedList< String > snippetList = Arrays.stream( line.split( "," ) )
                        .collect( Collectors.toCollection( LinkedList::new ) );
                if( !isRightFormat2( snippetList ) ) {
                    // Bad format
                    continue;
                }

                if( !localeName.equals( snippetList.removeFirst() ) ) {
                    // Another language
                    continue;
                }
                String question = snippetList.removeFirst();

                List< Variant > variantList = new ArrayList<>();
                Iterator< String > iterator = snippetList.iterator();
                while( iterator.hasNext() ) {
                    variantList.add( getVariant( iterator ) );
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

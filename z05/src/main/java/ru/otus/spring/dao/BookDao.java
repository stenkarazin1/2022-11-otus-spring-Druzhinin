package ru.otus.spring.dao;

import ru.otus.spring.exceptions.*;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.BookUniqueData;

import java.util.List;

public interface BookDao {

    int count();

    void insert( Book book ) throws LibraryException;

    Book getById( long id );

    Book getByBookUniqueData( BookUniqueData bookUniqueData ) throws LibraryException;

    List< Book > getAll() throws LibraryException;

    void updateAvailableQuantityByBookUniqueData( BookUniqueData bookUniqueData, int inc ) throws LibraryException;

    void deleteById( long id );

    void deleteByBookUniqueData( BookUniqueData bookUniqueData ) throws LibraryException;

}

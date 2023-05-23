package ru.otus.spring.dao;

import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.BookUniqueData;

import java.util.List;

public interface BookDao {

    int count();

    void insert( Book book );

    Book getById( long id );

    Book getByBookUniqueData( BookUniqueData bookUniqueData );

    List< Book > getAll();

    void deleteById( long id );

    void deleteByBookUniqueData( BookUniqueData bookUniqueData );

}

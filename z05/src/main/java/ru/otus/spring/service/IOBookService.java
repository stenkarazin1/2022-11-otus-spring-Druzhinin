package ru.otus.spring.service;

import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.BookUniqueData;

public interface IOBookService {

    BookUniqueData inputBookUniqueData();

    Book inputBookInfo();

    Book inputBookInfoWithDefaultBookInfo( Book defaultBookInfo );

    void printBookInfo( Book book );

}

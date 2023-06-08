package ru.otus.spring.dao;

import ru.otus.spring.exceptions.AuthorNotFoundException;
import ru.otus.spring.domain.Author;

public interface AuthorDao {

    long getAuthorId( Author author ) throws AuthorNotFoundException;

}

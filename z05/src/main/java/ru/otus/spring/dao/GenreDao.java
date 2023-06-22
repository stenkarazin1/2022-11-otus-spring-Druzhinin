package ru.otus.spring.dao;

import ru.otus.spring.exceptions.GenreNotFoundException;
import ru.otus.spring.domain.Genre;

public interface GenreDao {

    long getGenreId( Genre genre ) throws GenreNotFoundException;

}

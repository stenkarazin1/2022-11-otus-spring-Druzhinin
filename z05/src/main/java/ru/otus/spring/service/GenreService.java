package ru.otus.spring.service;

public interface GenreService {

    String inputGenreName();

    String inputGenreNameWithDefaultValue( String defaultValue );

    void printGenreName( String genreName );

}

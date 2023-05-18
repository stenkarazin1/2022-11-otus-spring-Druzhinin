package ru.otus.spring.service;

// import ru.otus.spring.exceptions.NoCinemaException;

public interface GenreService {

    String inputGenreName(); // throws NoCinemaException;

    void printGenreName( String genreName ); // throws NoCinemaException;

}

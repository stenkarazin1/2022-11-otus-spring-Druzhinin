package ru.otus.spring.exceptions;

public class BookAlreadyFoundException extends LibraryException {

   public BookAlreadyFoundException( String message ) {
       super( message );
   }
}

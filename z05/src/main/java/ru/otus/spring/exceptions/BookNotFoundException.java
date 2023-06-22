package ru.otus.spring.exceptions;

public class BookNotFoundException extends LibraryException {

   public BookNotFoundException( String message ) {
       super( message );
   }
}

package ru.otus.spring.exceptions;

public class LibraryException extends RuntimeException {

   public LibraryException( String message ) {
       super( message );
   }
}

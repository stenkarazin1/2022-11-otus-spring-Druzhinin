package ru.otus.spring.exceptions;

public class NegativeAvailableQuantityException extends LibraryException {

   public NegativeAvailableQuantityException( String message ) {
       super( message );
   }
}

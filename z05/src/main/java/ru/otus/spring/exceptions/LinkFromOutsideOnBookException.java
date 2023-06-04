package ru.otus.spring.exceptions;

public class LinkFromOutsideOnBookException extends LibraryException {

   public LinkFromOutsideOnBookException( String message ) {
       super( message );
   }
}

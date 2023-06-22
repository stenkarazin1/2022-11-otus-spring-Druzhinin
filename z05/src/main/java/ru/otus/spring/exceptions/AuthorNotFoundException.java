package ru.otus.spring.exceptions;

public class AuthorNotFoundException extends LinkedTableInformationNotFoundException {

   public AuthorNotFoundException( String message ) {
      super( message );
   }
   
}

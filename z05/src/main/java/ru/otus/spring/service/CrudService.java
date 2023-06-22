package ru.otus.spring.service;

public interface CrudService {

    void addBook();

    void browseBookInfo();

    void browseAllBookInfo();

    // void changeBookInfo();  // Этот метод нужен для полноты картины. Но он сложный и запутанный, а потому отвлекает от сути

    void putBook();
    void returnBook();

    void removeBook();

}

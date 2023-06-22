package ru.otus.spring.service;

import ru.otus.spring.exceptions.*;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.BookUniqueData;
import ru.otus.spring.dao.BookDao;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CrudServiceImpl implements CrudService {
    private final BookDao bookDao;
    private final IOService ioService;
    private final IOBookService ioBookService;

    public CrudServiceImpl( BookDao bookDao, IOService ioService, IOBookService ioBookService ) {
        this.bookDao = bookDao;
        this.ioService = ioService;
        this.ioBookService = ioBookService;
    }

    @Override
    public void addBook() {
        try {
            bookDao.insert( ioBookService.inputBookInfo() );
            ioService.outputString( "Книга добавлена" );
        }
        catch ( LinkedTableInformationNotFoundException e ) {
            if ( e instanceof AuthorNotFoundException ) {
                ioService.outputString( "Невозможно добавить книгу:\n  Автор не найден" );
            }
            else {
                if ( e instanceof GenreNotFoundException ) {
                    ioService.outputString("Невозможно добавить книгу:\n  Жанр не найден");
                }
                else {
                    ioService.outputString( "Невозможно добавить книгу:\n  Что-то не найдено" );
                }
            }
        }
        catch ( BookAlreadyFoundException e ) {
            ioService.outputString( "Невозможно добавить книгу:\n  Книга с введенными данными уже имеется в библиотеке" );
        }
        catch ( NegativeAvailableQuantityException e ) {
            ioService.outputString( "Невозможно добавить книгу:\n  Количество экземпляров отрицательно" );
        }
        catch ( LibraryException e ) {
            ioService.outputString( "Невозможно добавить книгу:\n  Неизвестная ошибка" );
        }
    }

    @Override
    public void browseBookInfo() {
        BookUniqueData bookUniqueData = ioBookService.inputBookUniqueData();
        try {
            Book result = bookDao.getByBookUniqueData(bookUniqueData);

            ioService.outputString("\n-----------------------------------------------------------------");
            ioBookService.printBookInfo(result);
            ioService.outputString("-----------------------------------------------------------------\n");
        }
        catch ( BookNotFoundException e ) {
            ioService.outputString( "Невозможно просмотреть информацию о книге:\n  Книга не найдена" );
        }
        catch ( NegativeAvailableQuantityException e ) {
            ioService.outputString( "Невозможно просмотреть информацию о книге:\n  Количество экземпляров отрицательно" );
        }
        catch ( LibraryException e ) {
            ioService.outputString( "Невозможно просмотреть информацию о книге:\n  Неизвестная ошибка" );
        }
    }

    @Override
    public void browseAllBookInfo() {
        try {
            List< Book > result = bookDao.getAll();

            ioService.outputString( "\n-----------------------------------------------------------------" );
            for ( Book book : result ) {
                ioBookService.printBookInfo( book );
                ioService.outputString( "-----------------------------------------------------------------" );
            }
            ioService.outputString( "\n" );
        }
        catch ( LibraryException e ) {
            ioService.outputString( "Невозможно просмотреть информацию обо всех книгах:\n  Неизвестная ошибка" );
        }
    }

    @Override
    public void putBook() {
        BookUniqueData bookUniqueData = ioBookService.inputBookUniqueData();
        try {
            bookDao.updateAvailableQuantityByBookUniqueData(bookUniqueData, -1);
            ioService.outputString( "Книга выдана" );
        }
        catch ( BookNotFoundException e ) {
            ioService.outputString( "Невозможно выдать книгу:\n  Книга не найдена" );
        }
        catch ( NegativeAvailableQuantityException e ) {
            ioService.outputString( "Невозможно выдать книгу:\n  В наличии нет ни одного экземпляра книги" );
        }
        catch ( LibraryException e ) {
            ioService.outputString("Невозможно выдать книгу:\n  Неизвестная ошибка");
        }
    }
    @Override
    public void returnBook() {
        BookUniqueData bookUniqueData = ioBookService.inputBookUniqueData();
        try {
            bookDao.updateAvailableQuantityByBookUniqueData( bookUniqueData, 1 );
            ioService.outputString( "Книга получена" );
        }
        catch ( BookNotFoundException e ) {
            ioService.outputString( "Невозможно вернуть книгу:\n  Книга не найдена" );
        }
        catch ( LibraryException e ) {
            ioService.outputString("Невозможно вернуть книгу:\n  Неизвестная ошибка");
        }
    }

    @Override
    public void removeBook() {
        BookUniqueData bookUniqueData = ioBookService.inputBookUniqueData();
        try {
            bookDao.deleteByBookUniqueData( bookUniqueData );
            ioService.outputString( "Книга удалена" );
        }
        catch ( BookAlreadyFoundException e ) {
            ioService.outputString( "Невозможно удалить книгу:\n  Книга не найдена" );
        }
        catch ( LinkFromOutsideOnBookException e ) {
            ioService.outputString( "Невозможно удалить книгу:\n  На книгу есть ссылка извне" );
        }
        catch ( LibraryException e ) {
            ioService.outputString( "Невозможно удалить книгу:\n  Неизвестная ошибка" );
        }
    }
}

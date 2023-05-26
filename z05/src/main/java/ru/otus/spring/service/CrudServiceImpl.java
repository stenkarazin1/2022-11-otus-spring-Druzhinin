package ru.otus.spring.service;

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
        bookDao.insert( ioBookService.inputBookInfo() );
    }

    @Override
    public void browseBookInfo() {
        BookUniqueData bookUniqueData = ioBookService.inputBookUniqueData();
        Book result = bookDao.getByBookUniqueData( bookUniqueData );

        ioService.outputString( "\n-----------------------------------------------------------------" );
        ioBookService.printBookInfo( result );
        ioService.outputString( "-----------------------------------------------------------------\n" );
    }

    @Override
    public void browseAllBookInfo() {
        List< Book > result = bookDao.getAll();

        ioService.outputString( "\n-----------------------------------------------------------------" );
        for ( Book book : result ) {
            ioBookService.printBookInfo( book );
            ioService.outputString( "-----------------------------------------------------------------" );
        }
        ioService.outputString( "\n" );
    }

    @Override
    public void putBook() {
        BookUniqueData bookUniqueData = ioBookService.inputBookUniqueData();
        bookDao.updateAvailableQuantityByBookUniqueData( bookUniqueData, -1 );
    }
    @Override
    public void returnBook() {
        BookUniqueData bookUniqueData = ioBookService.inputBookUniqueData();
        bookDao.updateAvailableQuantityByBookUniqueData( bookUniqueData, 1 );
    }

    @Override
    public void removeBook() {
        BookUniqueData bookUniqueData = ioBookService.inputBookUniqueData();
        bookDao.deleteByBookUniqueData( bookUniqueData );
    }
}

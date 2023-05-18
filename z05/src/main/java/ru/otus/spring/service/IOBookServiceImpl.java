package ru.otus.spring.service;

import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.BookUniqueData;

import org.springframework.stereotype.Service;

@Service
public class IOBookServiceImpl implements IOBookService {
    private final TitleService titleService;
    private final AuthorService authorService;
    private final GenreService genreService;
    private final YearService yearService;
    private final QuantityService quantityService;

    IOBookServiceImpl( TitleService titleService, AuthorService authorService, GenreService genreService, YearService yearService, QuantityService quantityService ) {
        this.titleService = titleService;
        this.authorService = authorService;
        this.genreService = genreService;
        this.yearService = yearService;
        this.quantityService = quantityService;
    }

    @Override
    public BookUniqueData inputBookUniqueData() {
        String authorName = authorService.inputAuthorName();
        String title = titleService.inputTitle();
        int year = yearService.inputYear();
        return new BookUniqueData(title, authorName, year );
    }

    @Override
    public Book inputBookInfo() {
        BookUniqueData bookUniqueData = inputBookUniqueData();
        String genreName = genreService.inputGenreName();
        int quantity = quantityService.inputQuantity();
        return new Book( bookUniqueData.getTitle(), bookUniqueData.getYear_publication(), quantity, bookUniqueData.getAuthor_name(), genreName );
    }

    @Override
    public void printBookInfo( Book book ) {
        authorService.printAuthorName( book.getAuthor_name() );
        titleService.printTitle( book.getTitle() );
        genreService.printGenreName( book.getGenre_name() );
        yearService.printYear( book.getYear_publication() );
        quantityService.printQuantity( book.getAvailable_quantity() );
    }

}

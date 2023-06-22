package ru.otus.spring.dao;

import org.springframework.dao.IncorrectResultSizeDataAccessException;
import ru.otus.spring.exceptions.*;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.BookUniqueData;
import ru.otus.spring.domain.Genre;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class BookDaoJdbc implements BookDao {

    private final NamedParameterJdbcOperations namedParameterJdbcOperations;
    private final AuthorDao authorDao;
    private final GenreDao genreDao;

    public BookDaoJdbc( NamedParameterJdbcOperations namedParameterJdbcOperations, AuthorDao authorDao, GenreDao genreDao )
    {
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
        this.authorDao = authorDao;
        this.genreDao = genreDao;
    }

    @Override
    public int count() {
        MapSqlParameterSource params = new MapSqlParameterSource();

        return namedParameterJdbcOperations.queryForObject( "SELECT COUNT(*) FROM book", params, Integer.class);
    }

    @Override
    public void insert( Book book ) throws LibraryException {
        long authorId = authorDao.getAuthorId( new Author( book.getAuthor_name() ) );
        long genreId = genreDao.getGenreId( new Genre( book.getGenre_name() ) );

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue( "title", book.getTitle() );
        params.addValue( "year_publication", book.getYear_publication() );
        params.addValue( "available_quantity", book.getAvailable_quantity() );
        params.addValue( "author_id", authorId );
        params.addValue( "genre_id", genreId );

        try {
            long result = namedParameterJdbcOperations.update("INSERT INTO book (title,   year_publication,  available_quantity,  genre_id,  author_id) " +
                            "VALUES           (:title, :year_publication, :available_quantity, :genre_id, :author_id)",
                    params);
            if( result != 1 ) {
                throw new LibraryException( "UNKNOWN ERROR" );
            }
        }
        catch( DuplicateKeyException e ) {
            throw new BookAlreadyFoundException( "BOOK ALREADY FOUND" );
        }
        catch( DataIntegrityViolationException e ) {
            throw new NegativeAvailableQuantityException( "NEGATIVE AVAILABLE QUANTITY" );
        }
        catch ( DataAccessException e ) {
            throw new LibraryException( "UNKNOWN ERROR" );
        }
    }

    @Override
    public Book getById( long id ) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue( "id", id );

        return namedParameterJdbcOperations.queryForObject( "SELECT title, year_publication, available_quantity, author_name, genre_name " +
                        "FROM book " +
                        "     JOIN author USING (author_id) " +
                        "     JOIN genre USING (genre_id) " +
                        "WHERE book_id = :id",
                params, new BookMapper() );
    }

    @Override
    public Book getByBookUniqueData( BookUniqueData bookUniqueData ) throws LibraryException {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue( "author_name", bookUniqueData.getAuthor_name() );
        params.addValue( "title", bookUniqueData.getTitle() );
        params.addValue( "year_publication", bookUniqueData.getYear_publication() );

        try {
            return namedParameterJdbcOperations.queryForObject( "SELECT title, year_publication, available_quantity, author_name, genre_name " +
                            "FROM book " +
                            "     JOIN author USING (author_id) " +
                            "     JOIN genre USING (genre_id) " +
                            "WHERE author_name = :author_name " +
                            "      AND title = :title" +
                            "      AND year_publication = :year_publication",
                    params, new BookMapper() );
        }
        catch( IncorrectResultSizeDataAccessException e ) {
            throw new BookNotFoundException( "BOOK NOT FOUND" );
        }
        catch ( DataAccessException e ) {
            throw new LibraryException( "UNKNOWN ERROR" );
        }
    }

    @Override
    public List< Book > getAll() throws LibraryException {
        try {
            return namedParameterJdbcOperations.query( "SELECT title, year_publication, available_quantity, author_name, genre_name " +
                            "FROM book " +
                            "     JOIN author USING (author_id) " +
                            "     JOIN genre USING (genre_id) ",
                    new BookMapper() );
        }
        catch ( DataAccessException e ) {
            throw new LibraryException( "UNKNOWN ERROR" );
        }
    }

    @Override
    public void updateAvailableQuantityByBookUniqueData( BookUniqueData bookUniqueData, int increment ) throws LibraryException {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue( "author_name", bookUniqueData.getAuthor_name() );
        params.addValue( "title", bookUniqueData.getTitle() );
        params.addValue( "year_publication", bookUniqueData.getYear_publication() );
        params.addValue( "increment", increment );

        try {
            long result = namedParameterJdbcOperations.update( "UPDATE book " +
                        "SET available_quantity = available_quantity + :increment " +
                        "WHERE book_id IN " +
                        "( SELECT book_id " +
                        "  FROM book " +
                        "       JOIN author USING (author_id) " +
                        "  WHERE author_name = :author_name " +
                        "        AND title = :title " +
                        "        AND year_publication = :year_publication )",
                params );
            if( result != 1 ) {
                throw new BookNotFoundException( "BOOK NOT FOUND" );
            }
        }
        catch( DataIntegrityViolationException e ) {
            throw new NegativeAvailableQuantityException( "NEGATIVE AVAILABLE QUANTITY" );
        }
        catch ( DataAccessException e ) {
            throw new LibraryException( "UNKNOWN ERROR" );
        }
    }

    @Override
    public void deleteById( long id ) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue( "id", id );

        namedParameterJdbcOperations.update( "DELETE FROM book WHERE book_id = :id",
                params );
    }

    @Override
    public void deleteByBookUniqueData( BookUniqueData bookUniqueData ) throws LibraryException {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue( "author_name", bookUniqueData.getAuthor_name() );
        params.addValue( "title", bookUniqueData.getTitle() );
        params.addValue( "year_publication", bookUniqueData.getYear_publication() );

        try {
            long result = namedParameterJdbcOperations.update( "DELETE FROM book " +
                            "WHERE book_id IN " +
                            "( SELECT book_id " +
                            "  FROM book " +
                            "       JOIN author USING (author_id) " +
                            "  WHERE author_name = :author_name " +
                            "        AND title = :title " +
                            "        AND year_publication = :year_publication )",
                    params );
            if( result != 1 ) {
                throw new BookNotFoundException( "BOOK NOT FOUND" );
            }
        }
        catch( DataIntegrityViolationException e ) {
            throw new LinkFromOutsideOnBookException( "REFERENTIAL INTEGRITY VIOLATION" );
        }
        catch ( DataAccessException e ) {
            throw new LibraryException( "UNKNOWN ERROR" );
        }
    }

    private static class BookMapper implements RowMapper< Book > {

        @Override
        public Book mapRow( ResultSet resultSet, int i ) throws SQLException {
            String title = resultSet.getString( "title" );
            int year = resultSet.getInt( "year_publication" );
            int quantity = resultSet.getInt( "available_quantity" );
            String authorName = resultSet.getString( "author_name" );
            String genreName = resultSet.getString( "genre_name" );
            return new Book( title, year, quantity, authorName, genreName );
        }
    }
}

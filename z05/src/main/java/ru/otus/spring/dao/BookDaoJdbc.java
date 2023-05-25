package ru.otus.spring.dao;

import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.BookUniqueData;
import ru.otus.spring.domain.Genre;

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
    public void insert( Book book ) {
        long authorId = authorDao.getAuthorId( new Author( book.getAuthor_name() ) );
        long genreId = genreDao.getGenreId( new Genre( book.getGenre_name() ) );

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue( "title", book.getTitle() );
        params.addValue( "year_publication", book.getYear_publication() );
        params.addValue( "available_quantity", book.getAvailable_quantity() );
        params.addValue( "author_id", authorId );
        params.addValue( "genre_id", genreId );

        namedParameterJdbcOperations.update( "INSERT INTO book (title,   year_publication,  available_quantity,  genre_id,  author_id) " +
                                                 "VALUES           (:title, :year_publication, :available_quantity, :genre_id, :author_id)",
                params);
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
    public Book getByBookUniqueData( BookUniqueData bookUniqueData ) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue( "author_name", bookUniqueData.getAuthor_name() );
        params.addValue( "title", bookUniqueData.getTitle() );
        params.addValue( "year_publication", bookUniqueData.getYear_publication() );

        return namedParameterJdbcOperations.queryForObject( "SELECT title, year_publication, available_quantity, author_name, genre_name " +
                        "FROM book " +
                        "     JOIN author USING (author_id) " +
                        "     JOIN genre USING (genre_id) " +
                        "WHERE author_name = :author_name " +
                        "      AND title = :title" +
                        "      AND year_publication = :year_publication",
                params, new BookMapper() );
    }

    @Override
    public List< Book > getAll() {
        return namedParameterJdbcOperations.query( "SELECT title, year_publication, available_quantity, author_name, genre_name " +
                                                        "FROM book " +
                                                        "     JOIN author USING (author_id) " +
                                                        "     JOIN genre USING (genre_id) ",
                new BookMapper() );
    }

    @Override
    public void deleteById( long id ) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue( "id", id );

        namedParameterJdbcOperations.update( "DELETE FROM book WHERE book_id = :id",
                params );
    }

    @Override
    public void deleteByBookUniqueData( BookUniqueData bookUniqueData ) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue( "author_name", bookUniqueData.getAuthor_name() );
        params.addValue( "title", bookUniqueData.getTitle() );
        params.addValue( "year_publication", bookUniqueData.getYear_publication() );

        namedParameterJdbcOperations.update( "DELETE FROM book " +
                        "WHERE book_id IN " +
                        "(SELECT book_id " +
                        "FROM book " +
                        "     JOIN author USING (author_id) " +
                        "     JOIN genre USING (genre_id) " +
                        "WHERE author_name = :author_name " +
                        "      AND title = :title" +
                        "      AND year_publication = :year_publication)",
                params );
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

package ru.otus.spring.dao;

import ru.otus.spring.exceptions.*;
import ru.otus.spring.domain.Genre;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;

@Repository
public class GenreDaoJdbc implements GenreDao {

    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    public GenreDaoJdbc( NamedParameterJdbcOperations namedParameterJdbcOperations ) {
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
    }

    @Override
    public long getGenreId( Genre genre ) throws GenreNotFoundException {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue( "genre_name", genre.getGenreName() );

        /* В случае отсутствия в таблице жанров жанра, введенного пользователем, было бы правильно:
           1. спросить у пользователя разрешения на добавление в таблицу жанров нового жанра, и в случае положительного ответа сделать это
           2. оповестить пользователя об успехе или неудаче

           Однако реализовывать взаимодействие с пользователем в консольном приложении неудобно. И еще не хочется излишне усложнять программу
        */
        try {
            return namedParameterJdbcOperations.queryForObject( "SELECT genre_id " +
                        "FROM genre " +
                        "WHERE genre_name = :genre_name",
                params, Integer.class );
        }
        catch ( DataAccessException e ) {
            LinkedTableInformationNotFoundExceptionFactory factory = new LinkedTableInformationNotFoundExceptionFactory();
            throw factory.getLinkedTableInformationNotFoundException( LinkedTableInformationNotFoundExceptionTypes.GENRE );
        }
    }

}

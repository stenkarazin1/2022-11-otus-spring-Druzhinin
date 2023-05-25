package ru.otus.spring.dao;

import ru.otus.spring.domain.Genre;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class GenreDaoJdbc implements GenreDao {

    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    public GenreDaoJdbc( NamedParameterJdbcOperations namedParameterJdbcOperations ) {
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
    }

    @Override
    public long getGenreId( Genre genre ) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue( "genre_name", genre.getGenreName() );

        /* В случае отсутствия в таблице жанров жанра, введенного пользователем, было бы правильно:
           1. спросить у пользователя разрешения на добавление в таблицу жанров нового жанра, и в случае положительного ответа сделать это
           2. оповестить пользователя об успехе или неудаче

           Однако реализовывать взаимодействие с пользователем в консольном приложении неудобно. И еще не хочется излишне усложнять программу
        */
        return namedParameterJdbcOperations.queryForObject( "SELECT genre_id " +
                        "FROM genre " +
                        "WHERE genre_name = :genre_name",
                params, new GenreMapper() );
    }

    private static class GenreMapper implements RowMapper< Integer > {

        @Override
        public Integer mapRow( ResultSet resultSet, int i ) throws SQLException {
            return resultSet.getInt( "genre_id" );
        }
    }

}

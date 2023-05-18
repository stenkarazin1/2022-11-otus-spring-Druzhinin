package ru.otus.spring.dao;

import ru.otus.spring.domain.Genre;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class GenreDaoJdbc implements GenreDao {

    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    public GenreDaoJdbc( NamedParameterJdbcOperations namedParameterJdbcOperations ) {
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
    }

    @Override
    public int insert( Genre genre ) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue( "genre_name", genre.getGenreName() );
        KeyHolder keyHolder = new GeneratedKeyHolder();

        // Нужно вводить жанр, которого еще нет в БД, т.к. H2 эмулирует PostgreSQL в недостаточной степени
        namedParameterJdbcOperations.update( "INSERT INTO genre (genre_name) VALUES (:genre_name)", // ON CONFLICT (author_name) DO NOTHING
                params, keyHolder );

        return keyHolder.getKeyAs( Integer.class );
    }

}

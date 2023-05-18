package ru.otus.spring.dao;

import ru.otus.spring.domain.Author;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class AuthorDaoJdbc implements AuthorDao {

    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    public AuthorDaoJdbc( NamedParameterJdbcOperations namedParameterJdbcOperations ) {
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
    }

    @Override
    public int insert( Author author ) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue( "author_name", author.getAuthorName() );
        KeyHolder keyHolder = new GeneratedKeyHolder();

        // Нужно вводить автора, которого еще нет в БД, т.к. H2 эмулирует PostgreSQL в недостаточной степени
        namedParameterJdbcOperations.update( "INSERT INTO author (author_name) VALUES (:author_name)", // ON CONFLICT (author_name) DO NOTHING
                params, keyHolder );

        return keyHolder.getKeyAs( Integer.class );
    }

}

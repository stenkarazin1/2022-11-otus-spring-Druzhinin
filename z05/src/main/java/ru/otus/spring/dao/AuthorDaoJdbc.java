package ru.otus.spring.dao;

import ru.otus.spring.domain.Author;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class AuthorDaoJdbc implements AuthorDao {

    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    public AuthorDaoJdbc( NamedParameterJdbcOperations namedParameterJdbcOperations ) {
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
    }

    @Override
    public long getAuthorId( Author author ) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue( "author_name", author.getAuthorName() );

        /* В случае отсутствия в таблице авторов автора, введенного пользователем, было бы правильно:
           1. спросить у пользователя разрешения на добавление в таблицу авторов нового автора, и в случае положительного ответа сделать это
           2. оповестить пользователя об успехе или неудаче

           Однако реализовывать взаимодействие с пользователем в консольном приложении неудобно. И еще не хочется излишне усложнять программу
        */
        return namedParameterJdbcOperations.queryForObject( "SELECT author_id " +
                                                                 "FROM author " +
                                                                 "WHERE author_name = :author_name",
                params, new AuthorMapper() );
    }

    private static class AuthorMapper implements RowMapper< Integer > {

        @Override
        public Integer mapRow( ResultSet resultSet, int i ) throws SQLException {
            return resultSet.getInt( "author_id" );
        }
    }

}

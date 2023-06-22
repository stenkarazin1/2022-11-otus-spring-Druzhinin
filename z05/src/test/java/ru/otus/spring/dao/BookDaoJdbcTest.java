package ru.otus.spring.dao;

import org.h2.tools.Console;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.transaction.AfterTransaction;
import org.springframework.test.context.transaction.BeforeTransaction;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.domain.Book;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Dao для работы с пёрсонами должно")
@SpringBootTest
//@ActiveProfiles("test")
//@ExtendWith(SpringExtension.class)
//@ExtendWith( MockitoExtension.class )
@Import(BookDaoJdbc.class)
@Transactional(propagation = Propagation.NOT_SUPPORTED)
class BookDaoJdbcTest {

    private static final int EXPECTED_PERSONS_COUNT = 1;
    private static final int EXISTING_PERSON_ID = 1;
    private static final String EXISTING_PERSON_NAME = "Ivan";

    @MockBean
    private NamedParameterJdbcOperations namedParameterJdbcOperations;

    @MockBean
    private AuthorDaoJdbc authorDao;

    @MockBean
    private GenreDaoJdbc genreDao;

    @Autowired
    private BookDaoJdbc bookDao;

    @BeforeTransaction
    void beforeTransaction(){
        System.out.println("beforeTransaction");
    }

    @AfterTransaction
    void afterTransaction(){
        System.out.println("afterTransaction");
    }

    @DisplayName("возвращать ожидаемое количество пёрсонов в БД")
    @Test
    void shouldReturnExpectedPersonCount() {
        //Book book = bookDao.getById(1);
        //System.out.println(book.getTitle());

        int actualPersonsCount = bookDao.count();

        System.out.println("COUNT = " + actualPersonsCount);
        //Scanner input = new Scanner( System.in );
        //String inp = input.nextLine();
        //assertThat(actualPersonsCount).isEqualTo(EXPECTED_PERSONS_COUNT);
    }

    @DisplayName("добавлять пёрсона в БД")
    @Test
    void shouldInsertPerson() {
        Book expectedPerson = new Book("Мертвые души", 2,1900,"Гоголь Н.В.", "Постмодернизм");
        System.out.println("Title = " + expectedPerson.getTitle());
//        bookDao.insert(expectedPerson);
        //Book actualPerson = bookDao.getById(expectedPerson.getId());
        //assertThat(actualPerson).usingRecursiveComparison().isEqualTo(expectedPerson);
    }
}
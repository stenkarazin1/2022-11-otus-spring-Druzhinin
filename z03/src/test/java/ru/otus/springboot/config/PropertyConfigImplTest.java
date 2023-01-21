package ru.otus.springboot.config;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.Assertions.assertThatCode;

// Тест с поднятием контекста Spring Boot
@DisplayName( "Класс PropertyConfigImplTest" )
@SpringBootTest( classes = PropertyConfigImpl.class )
public class PropertyConfigImplTest {

    @MockBean
    ApplicationProperties ap;

    @Autowired
    PropertyConfig propertyConfig;

    @Test
    @DisplayName( "проверка метода getProperty(): если свойство найдено, не бросать исключение" )
    void shouldNotThrowExceptionIfPropertyExists() {
        assertThatCode( () -> propertyConfig.getProperty( "locale-name" ) ).doesNotThrowAnyException();
    }

}

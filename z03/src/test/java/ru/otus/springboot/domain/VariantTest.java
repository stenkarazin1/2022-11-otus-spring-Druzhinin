package ru.otus.springboot.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName( "Класс Variant" )
public class VariantTest {

    @DisplayName( "проверка метода getIsRight()" )
    @Test
    void shouldGetTrueFromGetter() {
        Variant variant = new Variant( "Потому что гладиолус", true );
        assertTrue( variant.getIsRight() );
    }

}

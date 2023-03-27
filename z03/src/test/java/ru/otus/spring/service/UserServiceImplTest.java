package ru.otus.spring.service;

import ru.otus.spring.exceptions.NoCinemaException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@DisplayName( "Класс UserServiceImpl" )
@ExtendWith( MockitoExtension.class )
class UserServiceImplTest {

    @Mock
    private IOService mockedIOService;

    @Mock
    private MessageSourceWrapper mockedMessageSourceWrapper;

    @DisplayName( "проверка метода inputUserName()" )
    @Test
    void shouldReturnUserName( ) {
        String userName = "John Doe";

        when( mockedMessageSourceWrapper.getMessage( anyString() ) ).thenReturn( "Name of user: " );
        when( mockedIOService.readStringWithPrompt( anyString() ) ).thenReturn( userName );

        UserService userService = new UserServiceImpl( mockedIOService, mockedMessageSourceWrapper );

        try {
            String str = userService.inputUserName();
        }
        catch ( NoCinemaException e ) {
        }
        verify( mockedMessageSourceWrapper, times( 1 ) ).getMessage( any() );
        verify( mockedIOService, times( 1 ) ).readStringWithPrompt( any() );

        try {
            assertEquals( userName, userService.inputUserName() );
        }
        catch ( NoCinemaException e ) {
        }
    }

}


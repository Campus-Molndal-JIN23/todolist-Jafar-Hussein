package campusmolndal.InputTest;

import org.campusmolndal.InputHanterare.InputHandler;
import org.junit.jupiter.api.*;

import org.mockito.Mock;

import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.when;

public class InputHandlerTest {
    @Mock
    private InputHandler inputHandler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() {
        // Perform any necessary cleanup
    }

    @Test
    void testGetIntInput() {
        int expected = 42;
        when(inputHandler.getIntInput()).thenReturn(expected);

        int actual = inputHandler.getIntInput();

        assertEquals(expected, actual);
    }
    @Test
    void testGetStringInput() {
        String expected = "test";
        when(inputHandler.getStringInput()).thenReturn(expected);

        String actual = inputHandler.getStringInput();

        assertEquals(expected, actual);
    }

    @Test
    void testGetTaskStatusInput() {
        boolean expected = true;
        when(inputHandler.getTaskStatusInput()).thenReturn(expected);

        boolean actual = inputHandler.getTaskStatusInput();

        assertEquals(expected, actual);
    }

}
package MenyTest;
import org.campusmolndal.MainMeny.Menu;
import org.campusmolndal.InputHanterare.InputHandler;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class MenuTest {

    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Mock
    private InputHandler inputHandler;

    private Menu menu;

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStream));
        MockitoAnnotations.initMocks(this);
        menu = new Menu();
        menu.inputHandler = inputHandler; // Manually inject the mocked inputHandler into the menu
    }

    @AfterEach
    public void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    public void testRun() {
        // Simulerar användarinput av "0" för att avsluta menyn
        when(inputHandler.getIntInput()).thenReturn(0);

        // Förväntad utdata
        String expected = """
                ==== ATT GÖRA-LISTA MENY ====
                1. Skapa Task
                2. Visa Task via ID
                3. Visa alla task
                4. Uppdatera task status
                5. Ta Bort task
                0. Avsluta
                =============================
                Välj ett alternativ: Avslutar...""";

        // Kör menyn
        menu.run();

        // Delar upp förväntad utdata och faktisk utdata i separata rader
        String[] expectedLines = expected.split("\\R");
        String[] actualLines = getOutput().split("\\R");

        // Jämför antalet rader i förväntad och faktisk utdata
        assertEquals(expectedLines.length, actualLines.length);
        int lineLength = expectedLines.length;

        // Jämför varje rad i förväntad utdata med faktisk utdata
        for (int i = 0; i < lineLength; i++) {
            assertEquals(expectedLines[i], actualLines[i]);
        }
    }


    private String getOutput() {
        return outputStream.toString();
    }

}

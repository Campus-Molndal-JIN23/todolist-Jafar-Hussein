package TodoListTest;

import org.bson.Document;
import org.campusmolndal.TodoList.Todo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TodoTest {
    private Todo sut;

    @BeforeEach
    public void setUp() {
        sut = new Todo(1, "Sample Title", false);
    }

    @Test
    public void testGetId() {
        Integer id = 2;
        Todo todo = new Todo(id, "Sample Title", false);

        // Kontrollerar att getId-metoden returnerar rätt id-värde
        assertEquals(id, todo.getId());
    }

    @Test
    public void testSetId() {
        Integer newId = 2;

        // Sätter ett nytt id med setId-metoden och kontrollerar att det nya id-värdet är korrekt
        sut.setId(newId);

        assertEquals(newId, sut.getId());
    }

    @Test
    public void testSetIdWrongInput() {
        Integer id = -1;
        sut = new Todo(1, "Sample Title", false);

        // Kontrollerar att IllegalArgumentException kastas om setId-metoden anropas med ogiltigt id-värde
        assertThrows(IllegalArgumentException.class, () -> sut.setId(id));
    }

    @Test
    public void testGetTitle() {
        String title = "Sample Title";
        Todo todo = new Todo(1, title, false);

        // Kontrollerar att getText-metoden returnerar rätt titel
        assertEquals(title, todo.getText());
    }

    @Test
    public void testSetTitle() {
        String newTitle = "New Title";
        Todo todo = new Todo(1, "Sample Title", false);

        // Sätter en ny titel med setTitle-metoden och kontrollerar att den nya titeln är korrekt
        todo.setTitle(newTitle);

        assertEquals(newTitle, todo.getText());
    }

    @Test
    public void testIsDone() {
        Boolean isDone = false;
        Todo todo = new Todo(1, "Sample Title", isDone);

        // Kontrollerar att getTaskStatus-metoden returnerar rätt status för att avsluta uppgiften
        assertEquals(isDone, todo.getTaskStatus());
    }

    @Test
    public void testSetTaskStatus() {
        Boolean newStatus = true;
        Todo todo = new Todo(1, "Sample Title", false);

        // Sätter en ny status för att avsluta uppgiften med setTaskStatus-metoden och kontrollerar att den nya statusen är korrekt
        todo.setTaskStatus(newStatus);

        assertEquals(newStatus, todo.getTaskStatus());
    }
    @Test
    public void testIsTodoIdDuplicate() { // Testar om id är unikt
        // Skapar tre objekt med olika id
        Todo todo1 = new Todo(1, "Task 1", false);
        Todo todo2 = new Todo(2, "Task 2", true);
        Todo todo3 = new Todo(1, "Task 3", false);
        Integer expectedId = 1; // Förväntat id-värde

        assertEquals(expectedId, todo1.getId()); // Kontrollerar att id-värdet är en duplikat
        assertNotEquals(expectedId, todo2.getId()); // Kontrollerar att id-värdet inte är en duplikat
        assertEquals(expectedId, todo3.getId()); // Kontrollerar att id-värdet är en duplikat
    }
    @Test
    public void testToDoc() {
        Integer id = 1;
        String title = "Sample Title";
        Boolean isDone = false;
        Todo todo = new Todo(id, title, isDone);

        // Kontrollerar att toDoc-metoden returnerar en korrekt Document-objekt med rätt fältvärden
        Document document = todo.toDoc();

        assertEquals(id, document.getInteger("id"));
        assertEquals(title, document.getString("title"));
        assertEquals(isDone, document.getBoolean("isDone"));
    }

    @Test
    public void testFromDoc() {
        Integer id = 1;
        String title = "Sample Title";
        Boolean isDone = false;
        Document document = new Document("id", id)
                .append("title", title)
                .append("isDone", isDone);

        // Kontrollerar att fromDoc-metoden skapar en ny Todo-objekt med rätt värden från det givna Document-objektet
        Todo todo = Todo.fromDoc(document);

        assertEquals(id, todo.getId());
        assertEquals(title, todo.getText());
        assertEquals(isDone, todo.getTaskStatus());
    }
}

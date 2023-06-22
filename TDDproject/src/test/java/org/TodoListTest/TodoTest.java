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

       assertEquals(id, todo.getId());
    }

    @Test
    public void testSetId() {
        Integer newId = 2;

        sut.setId(newId);

        assertEquals(newId, sut.getId());
    }


    @Test
    public void testSetIdWrongInput() {
        Integer id = -1;
        sut = new Todo(1, "Sample Title", false);

        assertThrows(IllegalArgumentException.class, () -> sut.setId(id));

    }
    @Test
    public void testGetTitle() {
        String title = "Sample Title";
        Todo todo = new Todo(1, title, false);

        assertEquals(title, todo.getTitle());
    }

    @Test
    public void testSetTitle() {
        String newTitle = "New Title";
        Todo todo = new Todo(1, "Sample Title", false);

        todo.setTitle(newTitle);

       assertEquals(newTitle, todo.getTitle());
    }

    @Test
    public void testIsDone() {
        Boolean isDone = false;
        Todo todo = new Todo(1, "Sample Title", isDone);

        assertEquals(isDone, todo.getTaskStatus());
    }

    @Test
    public void testSetTaskStatus() {
        Boolean newStatus = true;
        Todo todo = new Todo(1, "Sample Title", false);

        todo.setTaskStatus(newStatus);

       assertEquals(newStatus, todo.getTaskStatus());
    }

    @Test
    public void testToDoc() {
        Integer id = 1;
        String title = "Sample Title";
        Boolean isDone = false;
        Todo todo = new Todo(id, title, isDone);

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

        Todo todo = Todo.fromDoc(document);

        assertEquals(id, todo.getId());
        assertEquals(title, todo.getTitle());
        assertEquals(isDone, todo.getTaskStatus());
    }
}

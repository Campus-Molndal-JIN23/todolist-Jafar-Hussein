package campusmolndal.DatabasTest;

import org.campusmolndal.Database.MongoDb;
import org.campusmolndal.Database.MongoDbFacade;
import org.campusmolndal.TodoList.Todo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

public class MongoDbFacadeTest {
    @Mock
    private MongoDb mongoDb;

    private MongoDbFacade mongoDbFacade;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mongoDbFacade = new MongoDbFacade();
        mongoDbFacade.mongoDb = mongoDb;
    }

    @Test
    public void testCreateTodoItem() {
        Todo todo = new Todo(1, "Sample Todo", false);

        // Kontrollerar att createTodoItem-metoden i mongoDb anropas en gång med rätt parameter
        mongoDbFacade.createTodoItem(todo);

        verify(mongoDb, times(1)).createTodoItem(todo);
    }

    @Test
    public void testGetTodoItemById() {
        int id = 1;
        Todo expectedTodo = new Todo(1, "Sample Todo", false);

        // Sätter upp ett mock-svar för getTodoItemById-metoden i mongoDb
        when(mongoDb.getTodoItemById(id)).thenReturn(expectedTodo);

        // Anropar getTodoItemById-metoden i mongoDbFacade och kontrollerar att den returnerar förväntat värde
        Todo actualTodo = mongoDbFacade.getTodoItemById(id);

        verify(mongoDb, times(1)).getTodoItemById(id);
        assertEquals(expectedTodo, actualTodo);
    }

    @Test
    public void testGetAllTodoItems() {
        List<Todo> expectedTodos = new ArrayList<>();
        expectedTodos.add(new Todo(1, "Todo 1", false));
        expectedTodos.add(new Todo(2, "Todo 2", true));

        // Sätter upp ett mock-svar för getAllTodoItems-metoden i mongoDb
        when(mongoDb.getAllTodoItems()).thenReturn(expectedTodos);

        // Anropar getAllTodoItems-metoden i mongoDbFacade och kontrollerar att den returnerar förväntad lista av Todo-objekt
        List<Todo> actualTodos = mongoDbFacade.getAllTodoItems();

        verify(mongoDb, times(1)).getAllTodoItems();
        assertEquals(expectedTodos, actualTodos);
    }

    @Test
    public void testUpdateTodoStatus() {
        Integer id = 1;
        boolean isDone = true;

        // Call the updateTodoStatus method in mongoDbFacade
        mongoDbFacade.updateTodoStatus(id, isDone);

        // Verify that the updateTodoStatus method in mongoDb is called once with the correct parameters
        verify(mongoDb, times(1)).updateTodoStatus(id, isDone);
    }
    @Test
    public void testUpdateTodoText() {
        Integer id = 1;
        String newText = "New Text";

        // Call the updateTodoText method in mongoDbFacade
        mongoDbFacade.updateTodoText(id, newText);

        // Verify that the updateTodoText method in mongoDb is called once with the correct parameters
        verify(mongoDb, times(1)).updateTodoText(id, newText);
    }


    @Test
    public void testDeleteTodoItemById() {
        int id = 1;

        // Kontrollerar att deleteTodoItemById-metoden i mongoDb anropas en gång med rätt parameter
        mongoDbFacade.deleteTodoItemById(id);

        verify(mongoDb, times(1)).deleteTodoItemById(id);
    }

    @Test
    public void testViewTodoItemById() {
        int id = 1;

        // Kontrollerar att viewTodoItemById-metoden i mongoDb anropas en gång med rätt parameter
        mongoDbFacade.viewTodoItemById(id);

        verify(mongoDb, times(1)).viewTodoItemById(id);
    }
}

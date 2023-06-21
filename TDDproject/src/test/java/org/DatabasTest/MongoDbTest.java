package DatabasTest;

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

public class MongoDbTest {
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

        mongoDbFacade.createTodoItem(todo);

        verify(mongoDb, times(1)).createTodoItem(todo);
    }

    @Test
    public void testGetTodoItemById() {
        int id = 1;
        Todo expectedTodo = new Todo(1, "Sample Todo", false);

        when(mongoDb.getTodoItemById(id)).thenReturn(expectedTodo);

        Todo actualTodo = mongoDbFacade.getTodoItemById(id);

        verify(mongoDb, times(1)).getTodoItemById(id);
        assertEquals(expectedTodo, actualTodo);
    }

    @Test
    public void testGetAllTodoItems() {
        List<Todo> expectedTodos = new ArrayList<>();
        expectedTodos.add(new Todo(1, "Todo 1", false));
        expectedTodos.add(new Todo(2, "Todo 2", true));

        when(mongoDb.getAllTodoItems()).thenReturn(expectedTodos);

        List<Todo> actualTodos = mongoDbFacade.getAllTodoItems();

        verify(mongoDb, times(1)).getAllTodoItems();
        assertEquals(expectedTodos, actualTodos);
    }

    @Test
    public void testUpdateTodoDoneStatus() {
        int id = 1;
        boolean done = true;

        mongoDbFacade.updateTodoDoneStatus(id, done);

        verify(mongoDb, times(1)).updateTodoDoneStatus(id, done);
    }

    @Test
    public void testDeleteTodoItemById() {
        int id = 1;

        mongoDbFacade.deleteTodoItemById(id);

        verify(mongoDb, times(1)).deleteTodoItemById(id);
    }

    @Test
    public void testViewTodoItemById() {
        int id = 1;

        mongoDbFacade.viewTodoItemById(id);

        verify(mongoDb, times(1)).viewTodoItemById(id);
    }
}

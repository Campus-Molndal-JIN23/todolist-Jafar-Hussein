package DatabasTest;

import Databas.MongoDb;
import Databas.MongoDbFacade;
import TodoList.Todo;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class MongoDbTest {
    @Mock
    private MongoClient mockClient;

    @Mock
   private MongoCollection<Document> mockCollection;
    private MongoDb sut;
    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        sut= new MongoDb();
        sut.mongoClient = mockClient;
        sut.todoCollection = mockCollection;
    }
}

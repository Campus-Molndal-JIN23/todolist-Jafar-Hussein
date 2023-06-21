package Databas;

import TodoList.Todo;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import com.mongodb.*;

import java.util.Arrays;
import java.util.List;

public class MongoDb {
    private MongoClient mongoClient;
    private MongoDatabase dbName;
    private MongoCollection<Document> collection;
    private String connString = "mongodb://localhost:27017";

    public MongoDb() {
        // Connect to the MongoDB database
        ServerAddress serverAddress = new ServerAddress(connString); // Replace with your MongoDB server host and port

        // Create a MongoClientSettings object with the ServerAddress
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyToClusterSettings(builder ->
                        builder.hosts(Arrays.asList(serverAddress)))
                .build();

        // Create a MongoClient object using the MongoClientSettings
        mongoClient = MongoClients.create(settings); // Use the class-level variable instead of creating a new local variable
        dbName = mongoClient.getDatabase("todoList");
        collection = dbName.getCollection("todos");
    }



    public void AddTodo() {
    }
    public Todo GetTodoById() {
        return null;
    }
    public void getTodoItemById(int id) {
    }
    public List<Todo> GetAllTodos() {
        return null;
    }

    public void updateTodoTaskById(String task , int id) {
    }
    public void updateTodoTaskStatusById(int id) {
    }
    public void RemoveTodoById(int id) {
    }
    public void RemoveAllTodos() {
    }

}

package org.campusmolndal.Database;

import com.mongodb.client.*;
import org.bson.Document;
import org.campusmolndal.TodoList.Todo;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MongoDb {

    private final MongoCollection<Document> collection;
    private static final String collectionName = "todos";
    private static final String connString = "mongodb://localhost:27017";

    public MongoDb() {
        // Establish connection to the database
        MongoClient mongoClient = MongoClients.create(MongoDb.connString);
        MongoDatabase dbName = mongoClient.getDatabase("TodoList");
        collection = dbName.getCollection(MongoDb.collectionName);
    }

    public void createTodoItem(Todo todo) {
        // Convert Todo object to JSON
        JSONObject jsonTodo = new JSONObject();
        jsonTodo.put("id", todo.getId());
        jsonTodo.put("text", todo.getText());
        jsonTodo.put("done", todo.getTaskStatus());

        // Insert JSON document into the collection
        collection.insertOne(Document.parse(jsonTodo.toString()));
    }

    public Todo getTodoItemById(Integer id) {
        Document filter = new Document("id", id);
        FindIterable<Document> documents = this.collection.find(filter);
        Document document = documents.first();

        if (document != null) {
            // Convert JSON document to Todo object
            JSONObject jsonTodo = new JSONObject(document.toJson());
            Integer id1 = jsonTodo.getInt("id");
            String text = jsonTodo.getString("text");
            boolean isDone = jsonTodo.getBoolean("done");
            return new Todo(id1, text, isDone);
        }

        return null;
    }

    public List<Todo> getAllTodoItems() {
        List<Todo> todos = new ArrayList<>();

        FindIterable<Document> documents = this.collection.find();
        for (Document document : documents) {
            // Convert JSON document to Todo object
            JSONObject jsonTodo = new JSONObject(document.toJson());
            Integer id = jsonTodo.getInt("id");
            String text = jsonTodo.getString("text");
            boolean isDone = jsonTodo.getBoolean("done");
            Todo todo = new Todo(id, text, isDone);
            todos.add(todo);
        }

        return todos;
    }

    public void updateTodoStatus(Integer id, boolean isDone) {
        Document filter = new Document("id", id);
        Document update = new Document("$set", new Document("done", isDone));
        collection.updateOne(filter, update);
    }

    public void updateTodoText(Integer id, String newText) {
        Document filter = new Document("id", id);
        Document update = new Document("$set", new Document("text", newText));
        collection.updateOne(filter, update);
    }

    public void deleteTodoItemById(Integer id) {
        Document filter = new Document("id", id);
        collection.deleteOne(filter);
    }

    public void viewTodoItemById(Integer id) {
        Document filter = new Document("id", id);
        FindIterable<Document> documents = this.collection.find(filter);
        Document document = documents.first();

        if (document != null) {
            // Convert JSON document to Todo object
            JSONObject jsonTodo = new JSONObject(document.toJson());
            Integer todoId = jsonTodo.getInt("id");
            String text = jsonTodo.getString("text");
            boolean isDone = jsonTodo.getBoolean("done");
            Todo todo = new Todo(todoId, text, isDone);
            System.out.println(todo);
        } else {
            System.out.println("Todo item not found.");
        }
    }
}

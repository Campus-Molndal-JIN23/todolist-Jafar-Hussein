package org.campusmolndal.Database;

import com.mongodb.client.*;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.campusmolndal.TodoList.Todo;

import java.util.ArrayList;
import java.util.List;

public class MongoDb {
    private final MongoCollection<Document> collection;
    private static final String collectionName = "todos";
    private static final String connString = "mongodb://localhost:27017";

    public MongoDb() {
        MongoClient mongoClient = MongoClients.create(connString);
        MongoDatabase dbName = mongoClient.getDatabase("Todo");
        collection = dbName.getCollection(collectionName);
    }

    public void createTodoItem(Todo todo) {
        Document document = todo.toDoc();
        collection.insertOne(document);
    }

    public Todo getTodoItemById(Integer id) {
        Bson filter = new Document("id", id);
        FindIterable<Document> documents = collection.find(filter);
        Document document = documents.first();

        if (null != document) {
            Integer id1 = document.getInteger("id");
            String title = document.getString("title");
            Boolean isDone = document.getBoolean("isDone");
            return new Todo(
                    id1,
                    title,
                    isDone
            );
        }

        return null;
    }

    public List<Todo> getAllTodoItems() {
        List<Todo> todos = new ArrayList<>();

        try (MongoCursor<Document> cursor = collection.find().iterator()) {
            while (cursor.hasNext()) {
                Document document = cursor.next();
                Todo todo = Todo.fromDoc(document);
                todos.add(todo);
            }
        }

        return todos;
    }

    public void updateTodoDoneStatus(Integer id, boolean isDone) {
        Bson filter = new Document("id", id);
        Document update = new Document("$set", new Document("isDone", isDone));
        collection.updateOne(filter, update);
    }

    public void deleteTodoItemById(Integer id) {
        Bson filter = new Document("id", id);
        collection.deleteOne(filter);
    }

    public void viewTodoItemById(Integer id) {
        Bson filter = new Document("id", id);
        FindIterable<Document> documents = collection.find(filter);
        Document document = documents.first();

        if (null != document) {
            Todo todo = Todo.fromDoc(document);
            Integer id1 = todo.getId();
            System.out.println("Todo ID: " + id1);
            String title = todo.getTitle();
            System.out.println("Title: " + title);
            Boolean done = todo.isDone();
            System.out.println("Done: " + done);
        } else {
            System.out.println("Todo item not found.");
        }
    }
}

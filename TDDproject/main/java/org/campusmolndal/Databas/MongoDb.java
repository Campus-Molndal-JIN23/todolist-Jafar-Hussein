package org.campusmolndal.Databas;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import com.mongodb.*;
import com.mongodb.client.MongoCursor;
import org.campusmolndal.TodoList.Todo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MongoDb {
    private final MongoClient mongoClient;
    MongoCollection<Document> collection;
   private final MongoDatabase dbName;
   private final String collectionName = "todos";
   private final String connString = "mongodb://localhost:27017";


    public MongoDb() {
        // Connect to the MongoDB database
        mongoClient = MongoClients.create(connString);
        this.dbName = mongoClient.getDatabase("TodoList");
        collection = dbName.getCollection(collectionName);

    }

    public void createTodoItem(Todo todo) {
        Document document = todo.toDoc();
        collection.insertOne(document);
    }

    public Todo getTodoItemById(int id) {
        // Create a filter to find the Todo item with the given id
        Document filter = new Document("id", id);

        // Find the document in the collection
        Document document = collection.find(filter).first();

        if (document != null) {
            Todo todoList = new Todo(
                    document.getInteger("id"),
                    document.getString("title"),
                    document.getBoolean("isDone")
            );

            return todoList;
        }

        return null;
    }

    public List<Todo> getAllTodoItems() {
        List<Todo> todos = new ArrayList<>();

        // Retrieve all documents from the collection
        try (MongoCursor<Document> cursor = collection.find().iterator()) {
            while (cursor.hasNext()) {
                Document document = cursor.next();

                // Create a Todo object from each document and add it to the list
                Todo todo = Todo.fromDoc(document);
                todos.add(todo);
            }
        }

        return todos;
    }

    public void updateTodoDoneStatus(int id, boolean done) {
        // Create a filter to find the Todo item with the given id
        Document filter = new Document("id", id);

        // Create an update with the new done status
        Document update = new Document("$set", new Document("isDone", done));

        // Update the document in the collection
        collection.updateOne(filter, update);
    }

    public void deleteTodoItemById(int id) {
        // Create a filter to find the Todo item with the given id
        Document filter = new Document("id", id);

        // Delete the document from the collection
        collection.deleteOne(filter);
    }


    public void viewTodoItemById(int id) {
        Document filter = new Document("id", id);
        Document document = collection.find(filter).first();

        if (document != null) {
            Todo todo = Todo.fromDoc(document);
            System.out.println("Todo ID: " + todo.getId());
            System.out.println("Title: " + todo.getTitle());
            System.out.println("Done: " + todo.isDone());
        } else {
            System.out.println("Todo item not found.");
        }
    }

}



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
        // Etablerar anslutning till databasen genom att skapa en instans av MongoClient
        MongoClient mongoClient = MongoClients.create(MongoDb.connString);

        // Hämtar databasen "TodoList" från MongoClient
        MongoDatabase dbName = mongoClient.getDatabase("TodoList");

        // Hämtar samlingen "todos" från databasen
        collection = dbName.getCollection(MongoDb.collectionName);
    }

    public void createTodoItem(Todo todo) {
        // Konverterar Todo-objektet till JSON genom att skapa en instans av JSONObject
        JSONObject jsonTodo = new JSONObject();
        jsonTodo.put("id", todo.getId());
        jsonTodo.put("text", todo.getText());
        jsonTodo.put("done", todo.getTaskStatus());

        // Infogar JSON-dokumentet i samlingen genom att konvertera det till en Document och anropa insertOne-metoden
        collection.insertOne(Document.parse(jsonTodo.toString()));
    }

    public Todo getTodoItemById(Integer id) {
        // Skapar ett filter-dokument baserat på id
        Document filter = new Document("id", id);

        // Utför en sökning i samlingen baserat på filter-dokumentet och returnerar det första matchande dokumentet
        FindIterable<Document> documents = this.collection.find(filter);
        Document document = documents.first();

        if (document != null) {
            // Konverterar JSON-dokumentet till Todo-objekt genom att extrahera värden från dokumentet
            JSONObject jsonTodo = new JSONObject(document.toJson());
            Integer id1 = jsonTodo.getInt("id");
            String text = jsonTodo.getString("text");
            boolean isDone = jsonTodo.getBoolean("done");

            // Skapar och returnerar ett nytt Todo-objekt
            return new Todo(id1, text, isDone);
        }

        return null;
    }

    public List<Todo> getAllTodoItems() {
        List<Todo> todos = new ArrayList<>();

        // Utför en sökning i samlingen för att hämta alla dokument
        FindIterable<Document> documents = this.collection.find();
        for (Document document : documents) {
            // Konverterar varje JSON-dokument till Todo-objekt genom att extrahera värden från dokumenten
            JSONObject jsonTodo = new JSONObject(document.toJson());
            Integer id = jsonTodo.getInt("id");
            String text = jsonTodo.getString("text");
            boolean isDone = jsonTodo.getBoolean("done");

            // Skapar och lägger till ett nytt Todo-objekt i listan av todos
            Todo todo = new Todo(id, text, isDone);
            todos.add(todo);
        }

        // Returnerar listan med todos
        return todos;
    }

    public void updateTodoStatus(Integer id, boolean isDone) {
        // Skapar ett filter-dokument baserat på id
        Document filter = new Document("id", id);

        // Skapar ett update-dokument som anger den nya statusen för todo-objektet
        Document update = new Document("$set", new Document("done", isDone));

        // Utför en uppdatering i samlingen baserat på filter- och update-dokumenten
        collection.updateOne(filter, update);
    }

    public void updateTodoText(Integer id, String newText) {
        // Skapar ett filter-dokument baserat på id
        Document filter = new Document("id", id);

        // Skapar ett update-dokument som anger den nya texten för todo-objektet
        Document update = new Document("$set", new Document("text", newText));

        // Utför en uppdatering i samlingen baserat på filter- och update-dokumenten
        collection.updateOne(filter, update);
    }

    public void deleteTodoItemById(Integer id) {
        // Skapar ett filter-dokument baserat på id
        Document filter = new Document("id", id);

        // Tar bort det första matchande dokumentet från samlingen baserat på filter-dokumentet
        collection.deleteOne(filter);
    }

    public void viewTodoItemById(Integer id) {
        // Skapar ett filter-dokument baserat på id
        Document filter = new Document("id", id);

        // Utför en sökning i samlingen baserat på filter-dokumentet och returnerar det första matchande dokumentet
        FindIterable<Document> documents = this.collection.find(filter);
        Document document = documents.first();

        if (document != null) {
            // Konverterar JSON-dokumentet till Todo-objekt genom att extrahera värden från dokumentet
            JSONObject jsonTodo = new JSONObject(document.toJson());
            Integer todoId = jsonTodo.getInt("id");
            String text = jsonTodo.getString("text");
            boolean isDone = jsonTodo.getBoolean("done");

            // Skapar ett Todo-objekt och skriver ut det
            Todo todo = new Todo(todoId, text, isDone);
            System.out.println(todo);
        } else {
            System.out.println("Todo item not found.");
        }
    }
}

package org.campusmolndal.Database;

import com.mongodb.client.*;
import org.bson.Document;
import org.campusmolndal.TodoList.Todo;

import java.util.ArrayList;
import java.util.List;

public class MongoDb {

    private final MongoCollection<Document> collection;
    private static final String collectionName = "todos";
    private static final String connString = "mongodb://localhost:27017";

    public MongoDb() {
        //kopplar upp mot databasen
        MongoClient mongoClient = MongoClients.create(MongoDb.connString);
        MongoDatabase dbName = mongoClient.getDatabase("TodoList");
        collection = dbName.getCollection(MongoDb.collectionName);
    }

    public void createTodoItem(Todo todo) { //skapar ett nytt todo objekt
        Document document = todo.toDoc();
        collection.insertOne(document);
    }

    public Todo getTodoItemById(Integer id) { //hämtar ett todo objekt med ett specifikt id
        Document filter = new Document("id", id); //skapar ett filter
        FindIterable<Document> documents = this.collection.find(filter); //hämtar ett todo objekt med ett specifikt id
        Document document = documents.first();

        if (null != document) { //om todo objektet finns så skapar vi ett nytt todo objekt
            //skapar ett nytt todo objekt med id, text och status
            Integer id1 = document.getInteger("id");
            String text = document.getString("text");
            Boolean isDone = document.getBoolean("done");
            return new Todo( //skapar ett nytt todo objekt
                    id1,
                    text,
                    isDone
            );
        }

        return null;
    }

    public List<Todo> getAllTodoItems() { //hämtar alla todo objekt
        List<Todo> todos = new ArrayList<>(); //skapar en lista med todo objekt

        FindIterable<Document> documents = this.collection.find(); //hämtar alla todo objekt
        try (MongoCursor<Document> cursor = documents.iterator()) { //loopar igenom alla todo objekt
            while (cursor.hasNext()) {
                Document document = cursor.next();
                Todo todo = Todo.fromDoc(document);
                todos.add(todo); //lägger till todo objektet i listan
            }
        }

        return todos; //returnerar listan med todo objekt
    }


    public void updateTodoStatus(Integer id, boolean isDone) { // uppdaterar status för ett todo uppdrag
        Document filter = new Document("id", id); //hittar todo objektet med id
        //uppdaterar statusen
        Document update = new Document("$set", new Document("done", isDone));
        collection.updateOne(filter, update);
    }

    public void updateTodoText(Integer id, String newText) { // uppdaterar text för ett todo uppdrag
        Document filter = new Document("id", id);  //hittar todo objektet med id
        Document update = new Document("$set", new Document("text", newText));
        collection.updateOne(filter, update); //uppdaterar texten
    }



    public void deleteTodoItemById(Integer id) { //tar bort ett todo objekt med ett specifikt id
        Document filter = new Document("id", id);
        collection.deleteOne(filter);
    }

    public void viewTodoItemById(Integer id) { //skriver ut ett todo objekt med ett specifikt id
        Document filter = new Document("id", id);
        FindIterable<Document> documents = this.collection.find(filter);
        Document document = documents.first();

        if (null != document) {
            Todo todo = Todo.fromDoc(document);
            System.out.println(todo); // skriver ut todo objektet
        } else {
            System.out.println("Todo item not found.");
        }
    }

}

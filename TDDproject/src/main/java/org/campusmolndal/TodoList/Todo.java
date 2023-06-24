package org.campusmolndal.TodoList;

import org.bson.Document;

import java.util.Objects;

public class Todo {
    private Integer id;
    private String text;
    private Boolean isDone;

    public Todo(Integer id, String text,  Boolean isDone) {
        this.id = id;
        this.text = text;
        this.isDone = isDone;
    }

    // Hämtar ID för uppgiften
    public Integer getId() {
        return this.id;
    }

    // Sätter ID för uppgiften
    public void setId(Integer userChoiceId) {
        if (userChoiceId <= 0) {
            throw new IllegalArgumentException("ID must be a positive non-zero value"); // om id är mindre än 0 så kastas ett undantag
        }
        this.id = userChoiceId;
    }
    public static boolean isTodoIdDuplicate(Integer id) {
        return id > 0;
    }   // Kollar om id är unikt eller inte genom att jämföra med id som finns i databasen och returnerar true eller false

    // Hämtar texten för uppgiften
    public String getText() {
        return this.text;
    } // returnerar texten för uppgiften

    // Sätter titeln för uppgiften
    public void setText(String userTextChoice) { // sätter titeln för uppgiften
        Objects.requireNonNull(userTextChoice, "Title cannot be null");
        if (userTextChoice.isBlank()) { // om titeln är tom så kastas ett undantag
            throw new IllegalArgumentException("Title cannot be empty");
        }
        this.text = userTextChoice; // annars sätts titeln
    }

    // Hämtar statusen för uppgiften
    public Boolean getTaskStatus() {
        return isDone;
    } // returnerar statusen för uppgiften

    // Sätter statusen för uppgiften
    public void setTaskStatus(Boolean isDone) { // sätter statusen för uppgiften
        if (null == isDone) { // om statusen är null så kastas ett undantag
            throw new IllegalArgumentException("isDone cannot be null"); // om statusen är null så kastas ett undantag
        }
        this.isDone = isDone;
    }

    // Konverterar Todo-objektet till en Document
    public Document toDoc() {
        return new Document("id", this.id) // konverterar Todo-objektet till en Document
                .append("text", this.text)
                .append("done", this.isDone);
    }

    // Skapar ett Todo-objekt från en Document
    public static Todo fromDoc(Document doc) {
        if (null == doc) { // om dokumentet är null så returneras ett nytt Todo-objekt
            return new Todo(0, "", false);
        }
        return new Todo( // skapar ett Todo-objekt från en Document
                doc.getInteger("id"),
                doc.getString("text"),
                doc.getBoolean("done")
        );
    }

    // Returnerar en strängrepresentation av Todo-objektet
    @Override
    public String toString() {
        return "Todo ID: " + id +
                "\nText: " + text +
                "\nDone: " + isDone;
    }
}

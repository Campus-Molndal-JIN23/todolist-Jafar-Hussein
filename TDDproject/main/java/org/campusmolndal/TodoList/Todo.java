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
            throw new IllegalArgumentException("ID must be a positive non-zero value");
        }
        this.id = userChoiceId;
    }
    public static boolean isTodoIdDuplicate(Integer id) {
        return id > 0;
    }

    // Hämtar texten för uppgiften
    public String getText() {
        return this.text;
    }

    // Sätter titeln för uppgiften
    public void setText(String userTextChoice) {
        Objects.requireNonNull(userTextChoice, "Title cannot be null");
        if (userTextChoice.isBlank()) { // om titeln är tom så kastas ett undantag
            throw new IllegalArgumentException("Title cannot be empty");
        }
        this.text = userTextChoice;
    }

    // Hämtar statusen för uppgiften
    public Boolean getTaskStatus() {
        return isDone;
    }

    // Sätter statusen för uppgiften
    public void setTaskStatus(Boolean isDone) {
        if (null == isDone) {
            throw new IllegalArgumentException("isDone cannot be null");
        }
        this.isDone = isDone;
    }

    // Konverterar Todo-objektet till en Document
    public Document toDoc() {
        return new Document("id", this.id)
                .append("text", this.text)
                .append("done", this.isDone);
    }

    // Skapar ett Todo-objekt från en Document
    public static Todo fromDoc(Document doc) {
        if (null == doc) {
            return new Todo(0, "", false);
        }
        return new Todo(
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

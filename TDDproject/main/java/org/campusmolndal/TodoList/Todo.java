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

    public Integer getId() {
        return this.id;
    }
    public void setId(Integer userChoiceId) {
        if (userChoiceId <= 0) {
            throw new IllegalArgumentException("ID must be a positive non-zero value");
        }
        this.id = userChoiceId;
    }



    public String getText() {
        return this.text;
    }
    public void setTitle(String userTitleChoice) {
        Objects.requireNonNull(userTitleChoice, "Title cannot be null");
        if (userTitleChoice.isBlank()) {
            throw new IllegalArgumentException("Title cannot be empty");
        }
        this.text = userTitleChoice;
    }

    public Boolean getTaskStatus() {
        return isDone;
    }
    public void setTaskStatus(Boolean isDone) {
        if (null == isDone) {
            throw new IllegalArgumentException("isDone cannot be null");
        }
        this.isDone = isDone;
    }
   public Document toDoc() {
       return new Document("id", this.id)
               .append("text", this.text)
               .append("done", this.isDone);
    }

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
    @Override
    public String toString() {
        return "Todo ID: " + id +
                "\nText: " + text +
                "\nDone: " + isDone;
    }
}

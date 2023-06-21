package org.campusmolndal.TodoList;

import org.bson.Document;

public class Todo {
    private Integer id;
    private String title;
    private Boolean isDone;

    public Todo(Integer id, String title,  Boolean isDone) {
        this.id = id;
        this.title = title;
        this.isDone = isDone;
    }

    public Integer getId() {
        return 0;
    }

    public String getTitle() {
        return "";
    }
    public void setTitle(String title) {
    }
    public Boolean isDone() {
        return null;
    }
    public void setTaskStatus(Boolean isDone) {
    }
   public Document toDoc() {
       return new Document("id", id)
               .append("title", title)
               .append("isDone", isDone);
    }

    public static Todo fromDoc(Document doc) {
        if (doc == null) {
            return new Todo(0, "", false);
        }
        return new Todo(
                doc.getInteger("id"),
                doc.getString("title"),
                doc.getBoolean("isDone")
        );
    }
}

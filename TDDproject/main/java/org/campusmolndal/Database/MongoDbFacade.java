package org.campusmolndal.Database;

import org.campusmolndal.TodoList.Todo;

import java.util.List;

public class MongoDbFacade {
    public MongoDb mongoDb;

    public MongoDbFacade() {
        this.mongoDb = new MongoDb();
    } // Constructor

    public void createTodoItem(Todo todo) {
        mongoDb.createTodoItem(todo);
    } //skapar ett nytt todo objekt

    public Todo getTodoItemById(Integer id) {//hämtar ett todo objekt med ett specifikt id
        return mongoDb.getTodoItemById(id);
    }

    public List<Todo> getAllTodoItems() { //hämtar alla todo objekt
        return mongoDb.getAllTodoItems();
    }

    public void updateTodoStatus(Integer id,  boolean isDone) { //uppdaterar ett todo objekt
        mongoDb.updateTodoStatus(id, isDone);
    }
    public void updateTodoText(Integer id, String text) { //uppdaterar ett todo objekt
        mongoDb.updateTodoText(id, text);
    }

    public void deleteTodoItemById(Integer id) { //tar bort ett todo objekt med ett specifikt id
        mongoDb.deleteTodoItemById(id);
    }

    public void viewTodoItemById(Integer id) { //skriver ut ett todo objekt med ett specifikt id
        mongoDb.viewTodoItemById(id);
    }
}

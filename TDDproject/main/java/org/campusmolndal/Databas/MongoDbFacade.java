package org.campusmolndal.Databas;

import org.campusmolndal.TodoList.Todo;

import java.util.List;

public class MongoDbFacade {
    public MongoDb mongoDb;
public MongoDbFacade() {
    this.mongoDb = new MongoDb();
}
    public void createTodoItem(Todo todo) {
        mongoDb.createTodoItem(todo);
    }

    public Todo getTodoItemById(int id) {
        return mongoDb.getTodoItemById(id);
    }

    public List<Todo> getAllTodoItems() {
        return mongoDb.getAllTodoItems();
    }

    public void updateTodoDoneStatus(int id, boolean done) {
        mongoDb.updateTodoDoneStatus(id, done);
    }

    public void deleteTodoItemById(int id) {
        mongoDb.deleteTodoItemById(id);
    }

    public void viewTodoItemById(int id) {
        mongoDb.viewTodoItemById(id);
    }
}

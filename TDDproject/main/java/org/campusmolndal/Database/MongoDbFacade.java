package org.campusmolndal.Database;

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

    public Todo getTodoItemById(Integer id) {
        return mongoDb.getTodoItemById(id);
    }

    public List<Todo> getAllTodoItems() {
        return mongoDb.getAllTodoItems();
    }

    public void updateTodoDoneStatus(Integer id, boolean done) {
        mongoDb.updateTodoDoneStatus(id, done);
    }

    public void deleteTodoItemById(Integer id) {
        mongoDb.deleteTodoItemById(id);
    }

    public void viewTodoItemById(Integer id) {
        mongoDb.viewTodoItemById(id);
    }
}

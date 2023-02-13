package com.example.backend;

import java.util.List;

public class DefaultTodoStore implements TodoStore {
    TodoRepository todoRepository;
    public DefaultTodoStore(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Override
    public List<Todo> getTodos() {
        return null;
    }
}

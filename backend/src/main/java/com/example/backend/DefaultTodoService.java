package com.example.backend;

import java.util.List;

public class DefaultTodoService implements TodoService {
    TodoStore todoStore;
    public DefaultTodoService(TodoStore todoStore) {
        this.todoStore = todoStore;
    }

    @Override
    public List<Todo> getTodos() {
        return todoStore.getTodos();
    }
}

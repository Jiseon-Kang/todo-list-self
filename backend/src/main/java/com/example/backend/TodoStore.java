package com.example.backend;

import java.util.List;

public interface TodoStore {
    List<Todo> getTodos();

    void addTodo(Todo todo);
}

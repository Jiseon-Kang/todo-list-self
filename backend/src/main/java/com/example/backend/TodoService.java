package com.example.backend;

import java.util.List;

public interface TodoService {
    List<Todo> getTodos();
    void addTodo(Todo todo);
}

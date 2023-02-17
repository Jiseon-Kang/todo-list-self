package com.example.backend;

import java.util.List;

public interface TodoService {
    List<Todo> getTodos();
    void addTodo(Todo todo);

    void deleteTodo(Long id);

    void updateTodo(Todo todo);
}

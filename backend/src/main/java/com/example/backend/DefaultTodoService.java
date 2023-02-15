package com.example.backend;

import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DefaultTodoService implements TodoService {
    TodoStore todoStore;
    public DefaultTodoService(TodoStore todoStore) {
        this.todoStore = todoStore;
    }

    @Override
    public List<Todo> getTodos() {
        return todoStore.getTodos();
    }

    @Override
    public void addTodo(Todo todo) {
        todoStore.addTodo(todo);
    }

    @Override
    public void deleteTodo(Long id) {
        todoStore.deleteTodo(id);
    }

}

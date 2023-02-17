package com.example.backend;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class DefaultTodoStore implements TodoStore {
    TodoRepository todoRepository;

    public DefaultTodoStore(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Override
    public List<Todo> getTodos() {
        List<Todo> todoList = new ArrayList<>();
        List<TodoEntity> todoEntityList = todoRepository.findAll();
        for (TodoEntity todoEntity : todoEntityList) {
            Todo todo = new Todo();
            todo.setId(todoEntity.getId() + "");
            todo.setContent((todoEntity.getContent()));
            todoList.add(todo);
        }
        return todoList;
    }

    @Override
    public void addTodo(Todo todo) {
        TodoEntity todoEntity = new TodoEntity();
        todoEntity.setContent(todo.getContent());
        todoRepository.save(todoEntity);
    }

    @Override
    public void deleteTodo(Long id) {
        todoRepository.deleteById(id);
    }

    @Override
    public void updateTodo(Todo todo) {
        TodoEntity todoEntity = new TodoEntity();
        todoEntity.setId(Long.parseLong(todo.getId()));
        todoEntity.setContent(todo.getContent());
        todoRepository.save(todoEntity);
    }
}

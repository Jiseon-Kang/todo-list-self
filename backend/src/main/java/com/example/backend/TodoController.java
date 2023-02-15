package com.example.backend;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TodoController {
    TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }


    @GetMapping("/health")
    public String health() {
        return "OK";
    }


    @GetMapping("/todo")
    public List<Todo> getTodos() {
        return todoService.getTodos();
    }

    @PostMapping("/todo")
    public void addTodo(@RequestBody Todo todo){
        todoService.addTodo(todo);
    }

    @DeleteMapping("/todo/{id}")
    public void deleteTodo(@PathVariable String id) {
        todoService.deleteTodo(Long.parseLong(id));
    }
    }

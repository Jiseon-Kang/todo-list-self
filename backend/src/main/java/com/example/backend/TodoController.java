package com.example.backend;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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

    public static interface TodoStore {
    }
}

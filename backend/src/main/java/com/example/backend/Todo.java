package com.example.backend;

import lombok.Getter;

@Getter
public class Todo {

    private String id;
    private String content;

    public Todo() {
    }

    public Todo(String id, String content) {
        this.id = id;
        this.content = content;
    }
}

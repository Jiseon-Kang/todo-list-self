package com.example.backend;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
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

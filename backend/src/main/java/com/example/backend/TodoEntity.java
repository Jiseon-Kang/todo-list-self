package com.example.backend;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class TodoEntity {
    @Id
    private long id;
    private String content;
}
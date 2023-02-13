package com.example.backend;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class DefaultTodoStoreTest {

    @Autowired
    TodoRepository todoRepository;

    @Test
    void getTodos() {
        TodoEntity todoEntity = new TodoEntity();
        todoEntity.setContent("Hello World-2");
        todoRepository.save(todoEntity);
        TodoStore todoStore = new DefaultTodoStore(todoRepository);
        todoStore.getTodos();


    }
}
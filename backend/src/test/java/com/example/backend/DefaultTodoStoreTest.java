package com.example.backend;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

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


        List<Todo> todoList = todoStore.getTodos();


        assertThat(todoList.get(0).getContent(),equalTo("Hello World-2"));
    }

    @Test
    void addTodo() {
        Todo todo = new Todo();
        todo.setContent("qwer");
        TodoStore todoStore = new DefaultTodoStore(todoRepository);


        todoStore.addTodo(todo);


        List<TodoEntity> todoEntityList = todoRepository.findAll();
        assertThat(todoEntityList.get(0).getContent(),equalTo("qwer"));
    }
}
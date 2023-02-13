package com.example.backend;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

class DefaultTodoServiceTest {

    @Test
    void getTodos() {
        TodoStore todoStore = Mockito.mock(TodoStore.class);
        TodoService todoService = new DefaultTodoService(todoStore);
        Mockito.when(todoStore.getTodos()).thenReturn(
                List.of(new Todo("0002", "Hello World-2"))
        );


        List<Todo> todos = todoService.getTodos();


        assertThat(todos.size(), equalTo(1));
        assertThat(todos.get(0).getId(), equalTo("0002"));
        assertThat(todos.get(0).getContent(),equalTo("Hello World-2"));





    }


}
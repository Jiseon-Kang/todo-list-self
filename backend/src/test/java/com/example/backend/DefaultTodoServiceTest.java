package com.example.backend;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.verify;

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
    @Test
    void deleteTodo() {
        TodoStore todoStore = Mockito.mock(TodoStore.class);
        TodoService todoService = new DefaultTodoService(todoStore);


        todoService.deleteTodo(1L);


        ArgumentCaptor<Long> argumentCaptor = ArgumentCaptor.forClass(Long.class);
        verify(todoStore).deleteTodo(argumentCaptor.capture());
        Long result = argumentCaptor.getValue();
        assertThat(result.longValue(),equalTo(1L));
    }

    @Test
    void addTodo() {
        Todo todo = new Todo();
        todo.setContent("Hello World-2");
        TodoStore todoStore = Mockito.mock(TodoStore.class);
        TodoService todoService = new DefaultTodoService(todoStore);


        todoService.addTodo(todo);


        ArgumentCaptor<Todo> argumentCaptor = ArgumentCaptor.forClass(Todo.class);
        verify(todoStore).addTodo(argumentCaptor.capture());
        Todo result = argumentCaptor.getValue();
        assertThat(result.getContent(),equalTo("Hello World-2"));
    }
}
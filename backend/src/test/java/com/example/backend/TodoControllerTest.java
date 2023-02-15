package com.example.backend;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.MatcherAssert.assertThat;

class TodoControllerTest {

    @Test
    void getTodos() throws Exception {
        TodoService todoService = Mockito.mock(TodoService.class);
        Mockito.when(todoService.getTodos()).thenReturn(List.of(new Todo("0002", "Hello World-2")));

        MockMvc sut = MockMvcBuilders.standaloneSetup(new TodoController(todoService)).build();

        sut.perform(MockMvcRequestBuilders.get("/todo")).andExpect(status().isOk()).andExpect(jsonPath("$[0].id").value("0002")).andExpect(jsonPath("$[0].content").value("Hello World-2"));
    }
    @Test
    void deleteTodos() throws Exception {
        TodoService todoService = Mockito.mock(TodoService.class);
        MockMvc sut = MockMvcBuilders.standaloneSetup(new TodoController(todoService)).build();


        sut.perform(MockMvcRequestBuilders.delete("/todo/1"));


        ArgumentCaptor<Long> argumentCaptor = ArgumentCaptor.forClass(Long.class);
        verify(todoService).deleteTodo(argumentCaptor.capture());
        Long result = argumentCaptor.getValue();
        assertThat(result.longValue(), equalTo(1L));

    }


    @Test
    void addTodo() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        Todo todo = new Todo();
        todo.setContent("Hello World-2");
        String jsonContent = objectMapper.writeValueAsString(todo);
        TodoService todoService = Mockito.mock(TodoService.class);
        MockMvc sut = MockMvcBuilders.standaloneSetup(new TodoController(todoService)).build();


        sut.perform(MockMvcRequestBuilders.post("/todo")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonContent));


        ArgumentCaptor<Todo> argumentCaptor = ArgumentCaptor.forClass(Todo.class);
        verify(todoService).addTodo(argumentCaptor.capture());
        Todo result = argumentCaptor.getValue();


        assertThat(result.getContent(), equalTo("Hello World-2"));
    }


}
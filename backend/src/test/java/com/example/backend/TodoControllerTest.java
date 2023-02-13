package com.example.backend;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


class TodoControllerTest {

    @Test
    void getTodos() throws Exception {
        TodoService todoService = Mockito.mock(TodoService.class);
        Mockito.when(todoService.getTodos()).thenReturn(
                List.of(new Todo("0002", "Hello World-2"))
        );

        MockMvc sut = MockMvcBuilders.standaloneSetup(new TodoController(todoService)).build();


        sut.perform(MockMvcRequestBuilders.get("/todo"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value("0002"))
                .andExpect(jsonPath("$[0].content").value("Hello World-2"))
        ;
    }
}
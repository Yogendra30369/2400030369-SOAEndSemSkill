package com.example.userservice.controller;

import com.example.userservice.model.User;
import com.example.userservice.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    void getAllUsers_shouldReturnUsers() throws Exception {
        when(userService.getAllUsers()).thenReturn(List.of(
                new User(1L, "Arun", "Testing"),
                new User(2L, "Priya", "Development")
        ));

        mockMvc.perform(get("/api/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Arun"))
                .andExpect(jsonPath("$[0].task").value("Testing"));

        verify(userService, times(1)).getAllUsers();
    }

    @Test
    void getUserById_shouldReturnUser() throws Exception {
        when(userService.getUserById(1L)).thenReturn(new User(1L, "Arun", "Testing"));

        mockMvc.perform(get("/api/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Arun"))
                .andExpect(jsonPath("$.task").value("Testing"));

        verify(userService).getUserById(1L);
    }

    @Test
    void getUserById_whenMissing_shouldReturn404() throws Exception {
        when(userService.getUserById(99L)).thenReturn(null);

        mockMvc.perform(get("/api/users/99"))
                .andExpect(status().isNotFound());

        verify(userService).getUserById(99L);
    }

    @Test
    void addUser_shouldReturnCreatedUser() throws Exception {
        when(userService.addUser(any(User.class)))
                .thenReturn(new User(3L, "Kiran", "Design"));

        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Kiran\",\"task\":\"Design\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(3))
                .andExpect(jsonPath("$.name").value("Kiran"))
                .andExpect(jsonPath("$.task").value("Design"));

        verify(userService).addUser(any(User.class));
    }
}

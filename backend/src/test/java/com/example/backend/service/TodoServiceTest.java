package com.example.backend.service;

import com.example.backend.model.TodoItem;
import com.example.backend.model.TodoStatus;
import com.example.backend.repository.TodoItemRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TodoServiceTest {
    TodoItem todo1_noID, todo2_noID, todo1_withID, todo2_withID;
    TodoItemRepo todoItemRepo;
    IDService idService;
    TodoService todoService;

    @BeforeEach
    void setUp() {
        todo1_noID = new TodoItem(null, "My first item", TodoStatus.OPEN);
        todo2_noID = new TodoItem(null, "My second item", TodoStatus.OPEN);
        todo1_withID = new TodoItem("123", "My first item", TodoStatus.OPEN);
        todo2_withID = new TodoItem("234", "My second item", TodoStatus.OPEN);
        todoItemRepo = mock(TodoItemRepo.class);
        idService = mock(IDService.class);
        todoService = new TodoService(todoItemRepo, idService);
    }

    @Test
    void addTodoItem() {
        // GIVEN
        when(idService.generateID()).thenReturn(todo1_withID.id());
        when(todoItemRepo.addTodoItem(todo1_withID)).thenReturn(todo1_withID);
        TodoItem expected = todo1_withID;

        // WHEN
        TodoItem actual = todoService.addTodoItem(todo1_noID);

        // THEN
        verify(idService).generateID();
        verify(todoItemRepo).addTodoItem(todo1_withID);
        assertEquals(expected, actual);
    }
}
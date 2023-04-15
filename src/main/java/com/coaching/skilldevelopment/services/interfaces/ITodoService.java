package com.coaching.skilldevelopment.services.interfaces;

import com.coaching.skilldevelopment.dto.Todo;
import com.coaching.skilldevelopment.exception.InvalidRequestException;

import java.util.List;

public interface ITodoService {

    List<Todo> getTodos(int userId);
    Todo getTodo(int todoId);
    void createTodo(Todo todo);
    void updateTodo(int todoId, Todo todo) throws InvalidRequestException;
    void deleteTodo(int todoId) throws InvalidRequestException;
}

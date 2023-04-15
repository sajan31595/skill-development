package com.coaching.skilldevelopment.repository.interfaces;

import com.coaching.skilldevelopment.dto.Todo;

import java.util.List;

public interface ITodoDao {

    List<Todo> getTodos(int userId);
    Todo getTodo(int todoId);
    void createTodo(Todo todo);
    void updateTodo(int todoId, Todo todo);
    void deleteTodo(int todoId);
}

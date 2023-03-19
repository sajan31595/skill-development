package com.coaching.skilldevelopment.services;

import com.coaching.skilldevelopment.dto.Todo;
import com.coaching.skilldevelopment.exception.InvalidRequestException;
import com.coaching.skilldevelopment.repository.interfaces.ITodoDao;
import com.coaching.skilldevelopment.services.interfaces.ITodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService implements ITodoService {

    @Autowired
    private ITodoDao todoDao;

    public List<Todo> getTodos(){
        return todoDao.getTodos();
    }

    @Override
    public Todo getTodo(int todoId) {
        return todoDao.getTodo(todoId);
    }

    @Override
    public void createTodo(Todo todo) {
        todoDao.createTodo(todo);
    }

    @Override
    public void updateTodo(int todoId, Todo todo) throws InvalidRequestException {
        Todo original = todoDao.getTodo(todoId);
        if(original==null) throw new InvalidRequestException("Invalid Todo");
        todoDao.updateTodo(todoId, todo);
    }

    @Override
    public void deleteTodo(int todoId) throws InvalidRequestException {
        Todo original = todoDao.getTodo(todoId);
        if(original==null) throw new InvalidRequestException("Invalid Todo");
        todoDao.deleteTodo(todoId);
    }
}

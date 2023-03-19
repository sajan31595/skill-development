package com.coaching.skilldevelopment.helper;

import com.coaching.skilldevelopment.dto.Todo;
import com.coaching.skilldevelopment.payload.TodoRequest;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TodoHelper {

    public Todo getTodo(TodoRequest request, boolean isCreateRequest) {
        Todo todo = new Todo();
        todo.setName(request.getName());
        todo.setDescription(request.getDescription());
        todo.setModifiedOn(new Date());
        if(isCreateRequest) todo.setCreatedOn(new Date());
        todo.setStatus(request.getStatus());
        return todo;
    }
}

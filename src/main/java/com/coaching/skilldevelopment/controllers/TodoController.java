package com.coaching.skilldevelopment.controllers;

import com.coaching.skilldevelopment.dto.Todo;
import com.coaching.skilldevelopment.exception.InvalidRequestException;
import com.coaching.skilldevelopment.helper.TodoHelper;
import com.coaching.skilldevelopment.payload.TodoRequest;
import com.coaching.skilldevelopment.services.interfaces.ITodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin(origins="*", maxAge = 3600)
@RequestMapping("/api/todo")
public class TodoController {

    @Autowired
    private ITodoService todoService;

    @Autowired
    private TodoHelper helper;

    @GetMapping("/")
    public ResponseEntity<?> getTodos() {
        List<Todo> todos = todoService.getTodos();
        return ResponseEntity.ok(todos);
    }

    @PostMapping("/")
    public ResponseEntity<?> createTodo(@RequestBody TodoRequest request) {
        Todo todo = helper.getTodo(request, true);
        todoService.createTodo(todo);
        return ResponseEntity.ok("Todo Created");
    }

    @GetMapping("/{todoId}")
    public ResponseEntity<?> getTodo(@PathVariable int todoId) {
        Todo todo = todoService.getTodo(todoId);
        return ResponseEntity.ok(todo);
    }

    @PutMapping("/{todoId}")
    public ResponseEntity<?> updateTodo(@PathVariable int todoId, @RequestBody TodoRequest request) throws InvalidRequestException {
        Todo todo = helper.getTodo(request, false);
        todoService.updateTodo(todoId, todo);
        todo = todoService.getTodo(todoId);
        return ResponseEntity.ok(todo);
    }

    @DeleteMapping("/{todoId}")
    public ResponseEntity<?> deleteTodo(@PathVariable int todoId) throws InvalidRequestException {
        todoService.deleteTodo(todoId);
        return ResponseEntity.ok("Deleted");
    }
}

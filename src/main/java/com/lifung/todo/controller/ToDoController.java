package com.lifung.todo.controller;

import com.lifung.todo.dto.ToDoReadDTO;
import com.lifung.todo.dto.ToDoWriteDTO;
import com.lifung.todo.service.ToDoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
public class ToDoController {

    private final ToDoService todoService;

    public ToDoController(ToDoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping("/{userId}")
    public List<ToDoReadDTO> getTodos(@PathVariable Long userId) {
        return todoService.getToDos(userId);
    }

    @PostMapping("/{userId}")
    public ResponseEntity createToDo(@PathVariable Long userId, @RequestBody ToDoWriteDTO toDoWriteDTO) {
        todoService.createToDo(userId, toDoWriteDTO);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity updateToDo(@PathVariable Long id,@RequestBody ToDoWriteDTO toDoWriteDTO) {
        todoService.updateToDo(id, toDoWriteDTO);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteToDo(@PathVariable Long id) {
        todoService.deleteToDo(id);
        return ResponseEntity.noContent().build();
    }
}

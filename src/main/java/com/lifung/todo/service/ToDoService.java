package com.lifung.todo.service;

import com.lifung.todo.dto.ToDoReadDTO;
import com.lifung.todo.dto.ToDoWriteDTO;

import java.util.List;

public interface ToDoService {

    List<ToDoReadDTO> getToDos(Long userId);
    ToDoReadDTO getToDo(Long id);
    void createToDo(Long userId, ToDoWriteDTO toDoWriteDTO);
    void updateToDo(Long id,ToDoWriteDTO toDoWriteDTO);
    void deleteToDo(Long id);
}

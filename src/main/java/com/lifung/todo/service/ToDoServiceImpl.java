package com.lifung.todo.service;

import com.lifung.todo.dto.ToDoReadDTO;
import com.lifung.todo.dto.ToDoWriteDTO;
import com.lifung.todo.dto.UserReadDTO;
import com.lifung.todo.entity.ToDo;
import com.lifung.todo.repositoy.ToDoRepository;
import com.lifung.todo.repositoy.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ToDoServiceImpl implements ToDoService {

    private final ToDoRepository toDoRepository;
    private final UserRepository userRepository;

    public ToDoServiceImpl(ToDoRepository toDoRepository, UserRepository userRepository) {
        this.toDoRepository = toDoRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<ToDoReadDTO> getToDos(Long userId) {
        return toDoRepository.getToDosByUserId(userId).stream()
                .map(t -> new ToDoReadDTO(t.getId(),
                        t.getDescription(), new UserReadDTO(t.getCreatedBy().getId(),
                        t.getCreatedBy().getUserName(),
                        t.getCreatedBy().getFullName(),
                        t.getCreatedBy().getEmail()),
                        t.getCreatedAt()))
                .collect(Collectors.toList());
    }

    @Override
    public ToDoReadDTO getToDo(Long id) {
        var optionalToDo = toDoRepository.findById(id);
        if (optionalToDo.isEmpty()) {
            return null;
        }
        var toDo = optionalToDo.get();
        var user = toDo.getCreatedBy();
        return new ToDoReadDTO(toDo.getId(),
                toDo.getDescription(),
                new UserReadDTO(user.getId(),
                        user.getUserName(),
                        user.getFullName(),
                        user.getEmail()),
                toDo.getCreatedAt());
    }

    @Override
    public void createToDo(Long userId, ToDoWriteDTO toDoWriteDTO) {
        var optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()) {
            throw new IllegalArgumentException("User id was invalid");
        }
        var user = optionalUser.get();
        var toDo = new ToDo(null, toDoWriteDTO.getDescription(), user, LocalDateTime.now());
        toDoRepository.save(toDo);
    }

    @Override
    public void updateToDo(Long id, ToDoWriteDTO toDoWriteDTO) {
        var optionalToDo = toDoRepository.findById(id);
        if (optionalToDo.isEmpty()) {
            throw new IllegalArgumentException("ToDo id was invalid");
        }
        var toDo = optionalToDo.get();
        toDo.setDescription(toDoWriteDTO.getDescription());
        toDoRepository.save(toDo);
    }

    @Override
    public void deleteToDo(Long id) {
        var optionalToDo = toDoRepository.findById(id);
        if (optionalToDo.isEmpty()) {
            throw new IllegalArgumentException("ToDo id was invalid");
        }
        var toDo = optionalToDo.get();
        toDoRepository.delete(toDo);
    }
}

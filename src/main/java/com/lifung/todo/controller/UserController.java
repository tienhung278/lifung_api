package com.lifung.todo.controller;

import com.lifung.todo.dto.UserReadDTO;
import com.lifung.todo.dto.UserWriteDTO;
import com.lifung.todo.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public List<UserReadDTO> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/{id}")
    public UserReadDTO getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }

    @PostMapping
    public ResponseEntity createUser(@RequestBody UserWriteDTO userWriteDTO) {
        userService.createUser(userWriteDTO);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity updateUser(@PathVariable Long id,@RequestBody UserWriteDTO userWriteDTO) {
        userService.updateUser(id, userWriteDTO);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity createUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}

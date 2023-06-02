package com.lifung.todo.service;

import com.lifung.todo.dto.UserReadDTO;
import com.lifung.todo.dto.UserWriteDTO;

import java.util.List;

public interface UserService {

    List<UserReadDTO> getUsers();
    UserReadDTO getUser(Long id);
    void createUser(UserWriteDTO userWriteDTO);
    void updateUser(Long id,UserWriteDTO userWriteDTO);
    void deleteUser(Long id);
}

package com.lifung.todo.service;

import com.lifung.todo.dto.UserReadDTO;
import com.lifung.todo.dto.UserWriteDTO;
import com.lifung.todo.entity.User;
import com.lifung.todo.repositoy.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserReadDTO> getUsers() {
        var users = userRepository.findAll();
        return users.stream()
                .map(u -> new UserReadDTO(u.getId(),
                        u.getUserName(),
                        u.getFullName(),
                        u.getEmail()))
                .collect(Collectors.toList());
    }

    @Override
    public UserReadDTO getUser(Long id) {
        var optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            return null;
        }
        User user = optionalUser.get();
        return new UserReadDTO(user.getId(),
                user.getUserName(),
                user.getFullName(),
                user.getEmail());
    }

    @Override
    public void createUser(UserWriteDTO userWriteDTO) {
        var user = new User(null, userWriteDTO.getUserName(),
                userWriteDTO.getFullName(),
                userWriteDTO.getEmail());
        userRepository.save(user);
    }

    @Override
    public void updateUser(Long id, UserWriteDTO userWriteDTO) {
        var optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            throw new IllegalArgumentException("User id was invalid");
        }
        var user = optionalUser.get();
        user.setUserName(userWriteDTO.getUserName());
        user.setEmail(userWriteDTO.getEmail());
        user.setFullName(userWriteDTO.getFullName());
        userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        var optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            throw new IllegalArgumentException("User id was invalid");
        }
        var user = optionalUser.get();
        userRepository.delete(user);
    }
}

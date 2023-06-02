package com.lifung.todo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ToDoReadDTO {

    private Long id;
    private String description;
    private UserReadDTO createdBy;
    private LocalDateTime createdAt;
}

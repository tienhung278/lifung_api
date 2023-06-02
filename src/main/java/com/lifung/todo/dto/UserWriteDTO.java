package com.lifung.todo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserWriteDTO {

    private String userName;
    private String fullName;
    private String email;
}

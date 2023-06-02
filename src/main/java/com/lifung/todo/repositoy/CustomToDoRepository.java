package com.lifung.todo.repositoy;

import com.lifung.todo.entity.ToDo;

import java.util.List;

public interface CustomToDoRepository {

    List<ToDo> getToDosByUserId(Long Id);
}

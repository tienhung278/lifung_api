package com.lifung.todo.repositoy;

import com.lifung.todo.entity.ToDo;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomToDoRepositoryImpl implements CustomToDoRepository{

    private final EntityManager entityManager;

    public CustomToDoRepositoryImpl(EntityManager entityManager) {

        this.entityManager = entityManager;
    }

    @Override
    public List<ToDo> getToDosByUserId(Long Id) {
        String sql = "SELECT t FROM ToDo t WHERE t.createdBy.id = :id";
        return entityManager.createQuery(sql, ToDo.class)
                .setParameter("id", Id)
                .getResultList();
    }
}

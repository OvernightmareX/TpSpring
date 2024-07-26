package org.example.exo6.repositories;

import org.example.exo6.entities.Todo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TodoRepository extends CrudRepository<Todo, UUID> {
}

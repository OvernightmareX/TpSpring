package org.example.exo6.services;

import org.example.exo6.entities.Todo;
import org.example.exo6.repositories.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class TodoService {
    private final TodoRepository todoRepository;
    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public Todo save(Todo todo) {
        return todoRepository.save(todo);
    }

    public List<Todo> getAllTodo() {
        List<Todo> todos = new ArrayList<>();
        todoRepository.findAll().forEach(todos::add);
        return todos;
    }

    public List<Todo> getAllValidTodo(){
        List<Todo> todos = new ArrayList<>();
        todoRepository.findAll().forEach(todos::add);
        return todos.stream().filter(Todo::isValid).toList();

    }

    public List<Todo> getAllNotValidTodo(){
        List<Todo> todos = new ArrayList<>();
        todoRepository.findAll().forEach(todos::add);
        return todos.stream().filter(todo -> !todo.isValid()).toList();
    }

    public Todo findById(UUID id) {
        return todoRepository.findById(id).orElse(null);
    }

    public Todo delete(UUID id) {
        Todo todo = findById(id);
        if(todo == null)
            return null;

        todoRepository.delete(todo);
        return todo;
    }
}

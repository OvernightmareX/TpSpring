package org.example.exo1.services;

import org.example.exo1.entities.Todo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TodoService {
    List<Todo> todos;

    public TodoService() {
        todos = new ArrayList<>();
        todos.add(new Todo("todo0", "todo0 description", true));
        todos.add(new Todo("todo1", "todo1 description", false));
        todos.add(new Todo("todo2", "todo2 description", true));
    }

    public List<Todo> getAllTodos(){
        return todos;
    }

    public Todo getTodoById(int id){
        return todos.get(id);
    }
}

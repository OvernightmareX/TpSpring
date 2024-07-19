package org.example.exo1.controllers;

import org.example.exo1.entities.Todo;
import org.example.exo1.services.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/todo")
public class TodoController {

    private final TodoService todoService;

    @Autowired
    public TodoController(TodoService service) {
        this.todoService = service;
    }

    @GetMapping
    public String getAllTodos(Model model) {
        model.addAttribute("allTodos", todoService.getAllTodos());
        return "allTodoPage";
    }

    @GetMapping("/{id}")
    public String getTodoById(Model model, @PathVariable String id){
        model.addAttribute("todo", todoService.getTodoById(Integer.parseInt(id)));
        return "todoPage";
    }

    @GetMapping("/json")
    @ResponseBody
    public List<Todo> getAllTodos() {
        return todoService.getAllTodos();
    }

    @GetMapping("/json/{id}")
    @ResponseBody
    public Todo getTodoById(@PathVariable String id){
        String test = String.valueOf(true);
        return todoService.getTodoById(Integer.parseInt(id));
    }
}

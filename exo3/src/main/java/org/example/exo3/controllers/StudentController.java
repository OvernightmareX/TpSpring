package org.example.exo3.controllers;

import org.example.exo3.entities.Student;
import org.example.exo3.services.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@Controller
public class StudentController {

    private StudentService studentService;

    public StudentController(final StudentService monService) {
        this.studentService = monService;
    }

    @GetMapping
    public String homePage(){
        return "home";
    }

    @GetMapping("/list")
    public String listPage(Model model){
        model.addAttribute("allStudent", studentService.getAllStudent());
        model.addAttribute("isFilter", false);
        return "list";
    }

    @GetMapping("/detail")
    public String detailPage(Model model, @RequestParam(value="uuid") UUID uuid){
        model.addAttribute("student", studentService.getStudentByUUID(uuid));
        return "detail";
    }

    @GetMapping("/add")
    public String addStudent(Model model){
        Student student = new Student();
        model.addAttribute("student", student);
        return "add";
    }

    @GetMapping("/update")
    public String updateStudent(Model model, @RequestParam(value="uuid") UUID uuid){
        model.addAttribute("student", studentService.getStudentByUUID(uuid));
        return "add";
    }

    @PostMapping(value = {"/add"})
    public String submitStudent(@ModelAttribute Student student, Model model){
        System.out.println(student.getId());
        if(student.getId() != null)
            studentService.updateStudent(student);
        else
            studentService.addStudent(student.getFirstname(), student.getLastname(), student.getAge(), student.getEmail());

        model.addAttribute("isFilter", false);
        return "redirect:/list";
    }

    @GetMapping("/search")
    public String searchPage(){
        return "search";
    }

    @PostMapping("/search")
    public String searchPage(Model model, @RequestParam("name") String searchedName){
        System.out.println(searchedName);
        model.addAttribute("allStudent", studentService.getStudentsByName(searchedName));
        model.addAttribute("isFilter", true);
        return "list";
    }

    @GetMapping("/delete")
    public String searchPage(@RequestParam(value="uuid") UUID uuid){
        studentService.deleteStudent(uuid);
        return "redirect:/list";
    }
}

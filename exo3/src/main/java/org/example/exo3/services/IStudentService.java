package org.example.exo3.services;

import org.example.exo3.entities.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public interface IStudentService {

    List<Student> getAllStudent();
    Student getStudentByUUID(UUID uuid) ;
    List<Student> getStudentsByName(String name) ;
    void addStudent(String firstname, String lastname, int age, String email) ;
    void updateStudent(Student student) ;
    void deleteStudent(UUID id) ;
}

package org.example.exo3.services;

import org.example.exo3.entities.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class StudentService implements IStudentService{
    private final List<Student> studentList;

    public StudentService() {
        studentList = new ArrayList<>();

        studentList.add(Student.builder().id(UUID.randomUUID()).lastname("Twix").firstname("Gauche").age(18).email("twixG@twitwi.choco").build());
        studentList.add(Student.builder().id(UUID.randomUUID()).lastname("Twix").firstname("Droit").age(19).email("twixD@twitwi.choco").build());
    }

    public List<Student> getAllStudent(){
        return studentList;
    }

    public Student getStudentByUUID(UUID uuid) {
        return studentList.stream().filter(student -> student.getId().equals(uuid)).findFirst().orElse(null);
    }

    public List<Student> getStudentsByName(String name) {
        List<Student> students = new ArrayList<>();
        students.addAll(studentList.stream().filter(student -> student.getFirstname().toUpperCase().startsWith(name.toUpperCase())).toList());
        students.addAll(studentList.stream().filter(student -> student.getLastname().toUpperCase().startsWith(name.toUpperCase())).toList());
        return students;
    }

    public void addStudent(String firstname, String lastname, int age, String email) {
        Student student = Student.builder()
                .id(UUID.randomUUID())
                .firstname(firstname)
                .lastname(lastname)
                .age(age)
                .email(email)
                .build();

        studentList.add(student);
    }

    public void updateStudent(Student student) {
        Student studentToUpdate = getStudentByUUID(student.getId());
        studentToUpdate.setFirstname(student.getFirstname());
        studentToUpdate.setLastname(student.getLastname());
        studentToUpdate.setAge(student.getAge());
        studentToUpdate.setEmail(student.getEmail());
    }

    public void deleteStudent(UUID id) {
        studentList.removeIf(student -> student.getId().equals(id));
    }

}

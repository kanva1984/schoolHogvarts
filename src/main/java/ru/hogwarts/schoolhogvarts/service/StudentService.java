package ru.hogwarts.schoolhogvarts.service;


import ru.hogwarts.schoolhogvarts.model.Student;

import java.util.List;

public interface StudentService {
    Student add(Student student);

    Student get(Long id);

    Student update(Long id, Student student);

    void remove(Long id);

    List<Student> getStudentByAge(int age);
}

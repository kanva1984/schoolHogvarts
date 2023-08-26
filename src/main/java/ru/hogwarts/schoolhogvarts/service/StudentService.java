package ru.hogwarts.schoolhogvarts.service;


import ru.hogwarts.schoolhogvarts.model.Faculty;
import ru.hogwarts.schoolhogvarts.model.Student;

import java.util.List;

public interface StudentService {
    Student add(Student student);

    Student get(Long id);

    Student update(Long id, Student student);

    void remove(Long id);

    List<Student> getStudentByAge(int age);
    List<Student> findStudentByAgeBetween(int min, int max);

    Faculty getFacultyByStudent(Long id);

    int getNumberOfStudents();

    int getAverageAgeOfStudents();

    List<Student> getLastFiveStudents();

//    List<Student> findStudentByName(String name);

    List<String> getStudentsNamesStartedFromA();

    Double getAverageAge();

}

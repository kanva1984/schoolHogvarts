package ru.hogwarts.schoolhogvarts.service;


import ru.hogwarts.schoolhogvarts.model.Faculty;
import ru.hogwarts.schoolhogvarts.model.Student;

import java.util.List;

public interface FacultyService {
    Faculty add(Faculty faculty);

    Faculty get(Long id);

    Faculty update(Long id, Faculty faculty);

    void remove(Long id);

    List<Faculty> getFacultyByColor(String color);
    List<Faculty> getFacultyByColorOrName(String color, String name);

    List<Student> getStudents(Long id);

    List<Faculty> findStudentsByNameOfFacultyAndColor(String name, String color);
}

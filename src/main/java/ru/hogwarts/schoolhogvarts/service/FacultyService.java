package ru.hogwarts.schoolhogvarts.service;


import ru.hogwarts.schoolhogvarts.model.Faculty;

import java.util.List;

public interface FacultyService {
    Faculty add(Faculty faculty);

    Faculty get(Long id);

    Faculty update(Long id, Faculty faculty);

    void remove(Long id);

    List<Faculty> getFacultyByColor(String color);
}

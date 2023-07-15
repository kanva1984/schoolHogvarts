package ru.hogwarts.schoolhogvarts.service.impl;

import org.springframework.stereotype.Service;
import ru.hogwarts.schoolhogvarts.model.Faculty;
import ru.hogwarts.schoolhogvarts.model.Student;
import ru.hogwarts.schoolhogvarts.service.FacultyService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FacultyServiceImpl implements FacultyService {
    private final Map<Long, Faculty> faculties = new HashMap<>();
    private static long counter = 0;


    @Override
    public Faculty add(Faculty faculty) {
        faculty.setId(++counter);
        faculties.put(faculty.getId(), faculty);
        return faculties.get(faculty.getId());
    }

    @Override
    public Faculty get(Long id) {
        return faculties.get(id);
    }

    @Override
    public Faculty update(Long id, Faculty faculty) {
        Faculty savedFaculty = faculties.get(id);
        if (savedFaculty == null) {
            return null;
        }
        savedFaculty.setColor(faculty.getColor());
        savedFaculty.setName(faculty.getName());

        return savedFaculty;
    }

    @Override
    public void remove(Long id) {

    }

    @Override
    public List<Faculty> getFacultyByColor(String color) {
        return faculties.values()
                .stream()
                .filter(it -> it.getColor().equals(color))
                .collect(Collectors.toList());
    }
}

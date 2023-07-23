package ru.hogwarts.schoolhogvarts.service.impl;

import org.springframework.stereotype.Service;
import ru.hogwarts.schoolhogvarts.model.Faculty;
import ru.hogwarts.schoolhogvarts.model.Student;
import ru.hogwarts.schoolhogvarts.repository.FacultyRepository;
import ru.hogwarts.schoolhogvarts.service.FacultyService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FacultyServiceImpl implements FacultyService {
    private final FacultyRepository facultyRepository;

    public FacultyServiceImpl(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }


    @Override
    public Faculty add(Faculty faculty) {

        return facultyRepository.save(faculty);
    }

    @Override
    public Faculty get(Long id) {
        return facultyRepository.findById(id).orElse(null);
    }

    @Override
    public Faculty update(Long id, Faculty faculty) {
        Faculty facultyFromDB = get(id);
        if (facultyFromDB == null) {
            return null;
        }
        facultyFromDB.setName(faculty.getName());
        facultyFromDB.setColor(faculty.getColor());
        return facultyRepository.save(facultyFromDB);
    }

    @Override
    public void remove(Long id) {
        facultyRepository.findById(id);

    }

    @Override
    public List<Faculty> getFacultyByColor(String color) {
        return facultyRepository.findByColor(color);
    }
}

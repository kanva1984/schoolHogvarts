package ru.hogwarts.schoolhogvarts.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.schoolhogvarts.model.Faculty;
import ru.hogwarts.schoolhogvarts.model.Student;
import ru.hogwarts.schoolhogvarts.repository.FacultyRepository;
import ru.hogwarts.schoolhogvarts.service.FacultyService;

import java.util.List;

@Service
public class FacultyServiceImpl implements FacultyService {
    private final FacultyRepository facultyRepository;
    private final Logger logger = LoggerFactory.getLogger(FacultyServiceImpl.class);
    public FacultyServiceImpl(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }


    @Override
    public Faculty add(Faculty faculty) {
        logger.info("Method add was invoked!");

        return facultyRepository.save(faculty);
    }

    @Override
    public Faculty get(Long id) {
        logger.info("Method get was invoked!");

        return facultyRepository.findById(id).orElse(null);
    }

    @Override
    public Faculty update(Long id, Faculty faculty) {
        logger.info("Method update was invoked!");

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
        logger.info("Method remove was invoked!");

        facultyRepository.findById(id);

    }

    @Override
    public List<Faculty> getFacultyByColor(String color) {
        logger.info("Method getFacultyByColor was invoked!");

        return facultyRepository.findByColor(color);
    }

    @Override
    public List<Faculty> getFacultyByColorOrName(String color, String name) {
        logger.info("Method getFacultyByColorOrName was invoked!");

        return facultyRepository.findByColorIgnoreCaseOrNameIgnoreCase(color, name);
    }

    @Override
    public List<Student> getStudents(Long id) {
        logger.info("Method getStudents was invoked!");

        return facultyRepository.findById(id)
                .map(Faculty::getStudents)
                .orElse(null);
    }

//    @Override
//    public List<Faculty> findStudentsByNameOfFacultyAndColor(String name, String color) {
//        return facultyRepository.findStudentsByNameOfFacultyAndColor(name, color);
//    }
}

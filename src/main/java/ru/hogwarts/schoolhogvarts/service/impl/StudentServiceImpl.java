package ru.hogwarts.schoolhogvarts.service.impl;

import org.springframework.stereotype.Service;
import ru.hogwarts.schoolhogvarts.model.Faculty;
import ru.hogwarts.schoolhogvarts.model.Student;
import ru.hogwarts.schoolhogvarts.repository.StudentRepository;
import ru.hogwarts.schoolhogvarts.service.StudentService;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    @Override
    public Student add(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student get(Long id) {

        return studentRepository.findById(id)
                .orElse(null);
    }

    @Override
    public Student update(Long id, Student student) {
        Student studentFromDB = get(id);
        if (studentFromDB == null) {
            return null;
        }
        studentFromDB.setName(student.getName());
        studentFromDB.setAge(student.getAge());
        return studentRepository.save(studentFromDB);
    }

    @Override
    public void remove(Long id) {

        studentRepository.deleteById(id);
    }

    @Override
    public List<Student> getStudentByAge(int age) {
        return studentRepository.findByAge(age);
    }

    @Override
    public List<Student> findStudentByAgeBetween(int min, int max) {
        return studentRepository.findByAgeBetween(min, max);
    }

    @Override
    public Faculty getFacultyByStudent(Long id) {
        return studentRepository.findById(id)
                .map(Student :: getFaculty)
                .orElse(null);
    }
}

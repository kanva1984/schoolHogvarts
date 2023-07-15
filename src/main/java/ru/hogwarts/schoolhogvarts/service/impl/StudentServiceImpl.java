package ru.hogwarts.schoolhogvarts.service.impl;

import org.springframework.stereotype.Service;
import ru.hogwarts.schoolhogvarts.model.Student;
import ru.hogwarts.schoolhogvarts.service.StudentService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {
    private final Map<Long, Student> students = new HashMap<>();

    private static long counter = 0;

    @Override
    public Student add(Student student) {
        student.setId(++counter);
        students.put(student.getId(), student);
        return students.get(student.getId());
    }

    @Override
    public Student get(Long id) {
        return students.get(id);
    }

    @Override
    public Student update(Long id, Student student) {
        Student savedStudent = students.get(id);
        if (savedStudent == null) {
            return null;
        }
        savedStudent.setAge(student.getAge());
        savedStudent.setName(student.getName());

        return savedStudent;
    }

    @Override
    public void remove(Long id) {
        students.remove(id);
    }

    @Override
    public List<Student> getStudentByAge(int age) {
        return students.values()
                .stream()
                .filter(it -> it.getAge() == age)
                .collect(Collectors.toList());
    }
}

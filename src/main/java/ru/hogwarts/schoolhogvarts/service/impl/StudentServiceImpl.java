package ru.hogwarts.schoolhogvarts.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.schoolhogvarts.model.Faculty;
import ru.hogwarts.schoolhogvarts.model.Student;
import ru.hogwarts.schoolhogvarts.repository.StudentRepository;
import ru.hogwarts.schoolhogvarts.service.StudentService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;

    private final Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

//    private final Object printNameLock = new Object();

    @Override
    public Student add(Student student) {
        logger.info("Method add was invoked!");
        return studentRepository.save(student);
    }

    @Override
    public Student get(Long id) {
        logger.info("Method get was invoked!");
        return studentRepository.findById(id)
                .orElse(null);
    }

    @Override
    public Student update(Long id, Student student) {
        logger.info("Method add update invoked!");
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
        logger.info("Method remove was invoked!");

        studentRepository.deleteById(id);
    }

    @Override
    public List<Student> getStudentByAge(int age) {
        logger.info("Method getStudentByAge was invoked!");
        return studentRepository.findByAge(age);
    }

    @Override
    public List<Student> findStudentByAgeBetween(int min, int max) {
        logger.info("Method findStudentByAgeBetween was invoked!");

        return studentRepository.findByAgeBetween(min, max);
    }

    @Override
    public Faculty getFacultyByStudent(Long id) {
        logger.info("Method getFacultyByStudent was invoked!");

        return studentRepository.findById(id)
                .map(Student::getFaculty)
                .orElse(null);
    }

    @Override
    public int getNumberOfStudents() {
        logger.info("Method getNumberOfStudents was invoked!");

        return studentRepository.getNumberOfStudents();
    }

    @Override
    public int getAverageAgeOfStudents() {
        logger.info("Method getAverageAgeOfStudents was invoked!");

        return studentRepository.getAverageAgeOfStudents();
    }

    @Override
    public List<Student> getLastFiveStudents() {
        logger.info("Method getLastFiveStudents was invoked!");

        return studentRepository.getLastFiveStudents();
    }

    @Override
    public List<String> getStudentsNamesStartedFromA() {

        return studentRepository.findAll()
                .stream()
                .map(Student::getName)
                .map(String::toUpperCase)
                .filter(it -> it.startsWith("A"))
                .collect(Collectors.toList());
    }

    @Override
    public Double getAverageAge() {
        return studentRepository.findAll()
                .stream()
                .mapToInt(Student::getAge)
                .average()
                .orElse(0.0);
    }

    @Override
    public void threads() {
        List<Student> students = studentRepository.findAll();

        printName(students.get(0));
        printName(students.get(1));

        new Thread(() -> {
            printName(students.get(2));
            printName(students.get(3));
        }).start();

        new Thread(() -> {
            printName(students.get(4));
            printName(students.get(5));
        }).start();

    }

    private void printName(Student student) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(student.getName());
    }

    @Override

    public void threadsSync() {
        List<Student> students = studentRepository.findAll();
        printNameSynchronize(students.get(0));
        printNameSynchronize(students.get(1));

        new Thread(() -> {
            printNameSynchronize(students.get(2));
            printNameSynchronize(students.get(3));
        }).start();

        new Thread(() -> {
            printNameSynchronize(students.get(4));
            printNameSynchronize(students.get(5));
        }).start();
    }

    private synchronized void printNameSynchronize(Student student) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(student.getName());
    }


//    @Override
//    public List<Student> findStudentByName(String name) {
//        logger.info("Method findStudentByName was invoked!");
//
//        return studentRepository.findStudentByName();
//    }

}

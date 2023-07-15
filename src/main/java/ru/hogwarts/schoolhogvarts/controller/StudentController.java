package ru.hogwarts.schoolhogvarts.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.schoolhogvarts.model.Student;
import ru.hogwarts.schoolhogvarts.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    private final StudentService studentService;
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/{id}")
    public Student get(@PathVariable long id) {
        return studentService.get(id);
    }

    @PostMapping
    public Student add(@RequestBody Student student) {
        return studentService.add(student);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> update(@PathVariable Long id, @RequestBody Student student) {
        Student savedStudent = studentService.update(id, student);
        if (savedStudent == null) {
            return ResponseEntity.badRequest().build();
        } else {
            return ResponseEntity.ok(savedStudent);
        }
    }

    @DeleteMapping
    public void delete(@PathVariable Long id) {
        studentService.remove(id);
    }

    @GetMapping("/age")
    public List<Student> getStudentsByAge(@RequestParam int age) {
        return studentService.getStudentByAge(age);
    }

}

package ru.hogwarts.schoolhogvarts.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.schoolhogvarts.model.Faculty;
import ru.hogwarts.schoolhogvarts.model.Student;
import ru.hogwarts.schoolhogvarts.service.FacultyService;

import java.util.List;

@RestController
@RequestMapping("/faculties")

public class FacultyController {
    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }


    @GetMapping("/{id}")
    public Faculty get(@PathVariable long id) {
        return facultyService.get(id);
    }

    @PostMapping
    public Faculty add(@RequestBody Faculty faculty) {
        return facultyService.add(faculty);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Faculty> update(@PathVariable Long id, @RequestBody Faculty faculty) {
        Faculty savedFaculty = facultyService.update(id, faculty);
        if (savedFaculty == null) {
            return ResponseEntity.badRequest().build();
        } else {
            return ResponseEntity.ok(savedFaculty);
        }
    }

    @DeleteMapping
    public void delete(@PathVariable Long id) {
       facultyService.remove(id);
    }

    @GetMapping("/color")
    public List<Faculty> getFacultyByColor(@RequestParam String color) {
        return facultyService.getFacultyByColor(color);
    }

    @GetMapping("/color_or_name")
    public List<Faculty> getFacultyByColorOrName(@RequestParam String color, @RequestParam String name) {
        return facultyService.getFacultyByColorOrName(color, name);
    }
    @GetMapping("/{id}/students")
    public List<Student> getStudents(@PathVariable long id) {

        return facultyService.getStudents(id);
    }

}

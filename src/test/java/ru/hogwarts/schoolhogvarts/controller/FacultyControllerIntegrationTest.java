package ru.hogwarts.schoolhogvarts.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.hogwarts.schoolhogvarts.model.Faculty;
import ru.hogwarts.schoolhogvarts.repository.FacultyRepository;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class FacultyControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private FacultyRepository facultyRepository;

    @AfterEach
    public void resetDB() {
        facultyRepository.deleteAll();
    }

    @Test
    void shouldCreateFaculty() {
        Faculty faculty = new Faculty();
        faculty.setName("Gryffindor");
        faculty.setColor("Color");

        ResponseEntity<Faculty> response = restTemplate.postForEntity("/faculties", faculty, Faculty.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getId()).isNotNull();
        assertThat(response.getBody().getName()).isEqualTo(faculty.getName());
        assertThat(response.getBody().getColor()).isEqualTo(faculty.getColor());
    }

    @Test
    void shouldDeleteFaculty() {
        Long facultyId = persistTestFaculty("Gryffindor", "Color").getId();

        restTemplate.delete("/faculties/{id}", facultyId);

        assertThat(facultyRepository.findById(facultyId)).isEmpty();

    }

    @Test
    void shouldGetFaculty() {

        Long facultyId = persistTestFaculty("Gryffindor", "Color").getId();

        ResponseEntity<Faculty> responseEntity = restTemplate.getForEntity("/faculties/{id}", Faculty.class, facultyId);

        Faculty faculty = responseEntity.getBody();
        assertThat(faculty).isNotNull();
        assertThat(faculty.getId()).isEqualTo(facultyId);

    }

    private Object persistTestFaculty(String name, String color) {
        Faculty faculty = new Faculty();
        faculty.setName(name);
        faculty.setColor(color);
        return facultyRepository.save(faculty);
    }

}

package ru.hogwarts.schoolhogvarts.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.hogwarts.schoolhogvarts.model.Faculty;
import ru.hogwarts.schoolhogvarts.model.Student;
import ru.hogwarts.schoolhogvarts.service.AvatarService;
import ru.hogwarts.schoolhogvarts.service.StudentService;

import java.util.List;

import static org.apache.logging.log4j.CloseableThreadContext.put;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.http.RequestEntity.delete;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StudentController.class)
public class StudentControllerWevMvcTest {
    private AvatarService avatarService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private StudentService studentService;

    @Test
    void shouldCreateStudent() throws Exception {
        Student student = new Student();
        student.setName("Harry Potter");
        student.setAge(25);

        when(studentService.add(student)).thenReturn(student);

        ResultActions resultActions = mockMvc.perform(post("/students")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(student))
        );

        resultActions
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$.name").value(student.getName()))
                .andExpect((ResultMatcher) jsonPath("$.age").value(student.getAge()))
                .andDo(print());

    }

    @Test
    void shouldReturnStudent() throws Exception {
        Student student = new Student(1L, "Harry Potter", 20);

        when(studentService.get(1L)).thenReturn(student);

        ResultActions resultActions = mockMvc.perform(post("/students/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        );

        resultActions
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$.id").value(student.getId()))
                .andExpect((ResultMatcher) jsonPath("$.name").value(student.getName()))
                .andExpect((ResultMatcher) jsonPath("$.age").value(student.getAge()))
                .andDo(print());


    }

    @Test
    void shouldUpdateStudent() throws Exception {
        Long studentId = 1L;
        Student student = new Student(1L, "Harry Potter", 20);

        when(studentService.update(studentId, student)).thenReturn(student);

        ResultActions resultActions = mockMvc.perform(put("/students/" + studentId))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(student)
                );

        resultActions
                .andExpect((ResultMatcher) jsonPath("$.id").value(student.getId()))
                .andExpect((ResultMatcher) jsonPath("$.name").value(student.getName()))
                .andExpect((ResultMatcher) jsonPath("$.age").value(student.getAge()))
                .andDo(print());

    }

    @Test
    void shouldDeleteStudentTest() throws Exception {

        Long studentId = 1L;

        ResultActions resultActions = mockMvc.perform(delete("/students/")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        );

        resultActions
                .andExpect(status().isOk())
                .andDo(print());


    }

    @Test
    void shouldReturnFaculty() throws Exception {

        Long studentId = 1L;

        Faculty faculty = new Faculty(1L, "Gryffindor", "green");

        when(studentService.getFacultyByStudent(studentId)).thenReturn(faculty);

        ResultActions resultActions = mockMvc.perform(get("/students/" + studentId + "/faculty")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        );

        resultActions
                .andExpect((ResultMatcher) jsonPath("$.id").value(faculty.getId()))
                .andExpect((ResultMatcher) jsonPath("$.name").value(faculty.getName()))
                .andExpect((ResultMatcher) jsonPath("$.color").value(faculty.getColor()))
                .andDo(print());


    }

    @Test
    void shouldGetStudentsByAge() throws Exception {
        Student student = new Student(1L, "Harry Potter", 20);


        when(studentService.getStudentByAge(student.getAge())).thenReturn((List<Student>) student);

        ResultActions resultActions = mockMvc.perform(get("/students/age")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        );

        resultActions
                .andExpect((ResultMatcher) jsonPath("$.id").value(student.getId()))
                .andExpect((ResultMatcher) jsonPath("$.name").value(student.getName()))
                .andExpect((ResultMatcher) jsonPath("$.age").value(student.getAge()))
                .andDo(print());
    }

    @Test
    void shouldFindByAgeBetween() throws Exception {
        Student student = new Student(1L, "Harry Potter", 20);
        int min = 10;
        int max = 25;


        when(studentService.findStudentByAgeBetween(min, max)).thenReturn((List<Student>) student);

        ResultActions resultActions = mockMvc.perform(get("/students/by_age_between")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        );

        resultActions
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$.id").value(student.getId()))
                .andExpect((ResultMatcher) jsonPath("$.name").value(student.getName()))
                .andExpect((ResultMatcher) jsonPath("$.age").value(student.getAge()))
                .andDo(print());

    }



}

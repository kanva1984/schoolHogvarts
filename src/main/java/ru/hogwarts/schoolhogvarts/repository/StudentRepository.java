package ru.hogwarts.schoolhogvarts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.hogwarts.schoolhogvarts.model.Faculty;
import ru.hogwarts.schoolhogvarts.model.Student;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByAge(int age);
    List<Student> findStudentByName(String name);

    List<Student> findByAgeBetween(int min, int max);



    @Query(value = "select count(*) from student", nativeQuery = true)
    int getNumberOfStudents();

    @Query(value = "select avg(age) from student", nativeQuery = true)
    int getAverageAgeOfStudents();

    @Query(value = "select * from student order by id desc limit 5", nativeQuery = true)
    List<Student> getLastFiveStudents();

//    @Query(value = "select count(*) from student", nativeQuery = true)
//    List<Student> findStudentByName();

}

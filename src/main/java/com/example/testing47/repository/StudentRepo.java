package com.example.testing47.repository;

import com.example.testing47.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentRepo extends JpaRepository<Student,Integer> {
    List<Student> findAllByFirstName(String firstName);
    Student findByEmail(String email);

    @Query("select s from Student s where s.age=:age")
    List<Student> myStudents(Integer age);
}

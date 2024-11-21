package com.example.testing47.repository;

import com.example.testing47.entity.Group;
import com.example.testing47.entity.Student;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
 class StudentRepoTest {

    @Autowired
    StudentRepo studentRepo;
    @Autowired
    GroupRepo groupRepo;
    @Test
    void findAllByFirstName() {
        List<Student> list = studentRepo.saveAll(List.of(
                Student.builder().id(1).firstName("ali").build(),
                Student.builder().id(1).firstName("ali").build()
        ));
        List<Student> students = studentRepo.findAllByFirstName("ali");
        Assertions.assertEquals(list, students);
    }
    @Test
    void findByEmail() {
    ///yozasizlar test
    }
    @Test
    void myStudents() {
        List<Student> list = studentRepo.saveAll(List.of(
                Student.builder().id(1).firstName("ali").age(12).build(),
                Student.builder().id(1).firstName("ali").age(12).build()
        ));
        List<Student> students = studentRepo.myStudents(12);
        Assertions.assertEquals(list, students);
    }
    @Test
    void saveStudent() {
        Group group = Group.builder().name("A").build();
        Group group1 = groupRepo.save(group);

        Student student = Student.builder().firstName("ali").group(group1).build();
        Student saved = studentRepo.save(student);
        Assertions.assertEquals(student, saved);
        Assertions.assertEquals(student.getFirstName(), saved.getFirstName());
        Assertions.assertEquals(group, saved.getGroup());
        Assertions.assertNotNull(saved);
    }
    @Test
    void deleteStudent() {
                                  //id(1) qilmaymiz, seq b.n ishla
        Student student = Student.builder().firstName("ali").build();
        Student saved = studentRepo.save(student);
        Integer id = saved.getId();

        studentRepo.deleteById(id);
//        studentRepo.deleteById(id+1);//xatolik
        Optional<Student> optional = studentRepo.findById(id);
        Assertions.assertTrue(optional.isEmpty());//bo'shmi
    }
}
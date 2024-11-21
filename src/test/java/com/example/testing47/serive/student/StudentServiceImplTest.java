package com.example.testing47.serive.student;

import com.example.testing47.dto.StudentDto;
import com.example.testing47.entity.Group;
import com.example.testing47.entity.Student;
import com.example.testing47.repository.GroupRepo;
import com.example.testing47.repository.StudentRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.w3c.dom.stylesheets.LinkStyle;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
class StudentServiceImplTest {

    @Mock
    StudentRepo studentRepo;
    @Mock
    GroupRepo groupRepo;
    @InjectMocks
    StudentServiceImpl studentService;
    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void getStudents() {

        List<Student> students = List.of(
                Student.builder().firstName("Ali").age(12).build(),
                Student.builder().firstName("Vali").age(13).build()
        );
        Mockito.when(studentRepo.findAll()).thenReturn(students);

        HttpEntity<?> res = studentService.getStudents();
        List<Student> body = (List<Student>) res.getBody();
        Assertions.assertEquals(body,students);
        Mockito.verify(studentRepo,Mockito.times(1)).findAll();

    }
    @Test
    void saveStudent() {

        StudentDto studentDto = new StudentDto("Ali","a@mail.com",12,1);


        ArgumentCaptor<Student> captor = ArgumentCaptor.forClass(Student.class);
        studentService.saveStudent(studentDto);
        Mockito.verify(studentRepo,Mockito.atLeast(1)).save(captor.capture());

        Student value = captor.getValue();
        Assertions.assertEquals(studentDto.firstName(), value.getFirstName());
        Assertions.assertEquals(studentDto.age(),value.getAge());
    }

    @Test
    void increaseAgeTest() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Student student = Student.builder().firstName("Ali").age(12).build();

        Method increaseAge = studentService.getClass().getDeclaredMethod("increaseAge", Student.class);
        increaseAge.setAccessible(true);
        increaseAge.invoke(studentService, student);

        ArgumentCaptor<Student> captor = ArgumentCaptor.forClass(Student.class);
        Mockito.verify(studentRepo, Mockito.times(1)).save(captor.capture());

        Student value = captor.getValue();
        Assertions.assertEquals(13,value.getAge());

    }
    @Test
    void editStudent() {
        //Mock data
        StudentDto studentDto = new StudentDto("Ali","a@mail.com",12,1);

        Student oldStudent = Student.builder().id(1).firstName("Vali").email("v@mail.com").age(12).build();
        Mockito.when(studentRepo.findById(1)).thenReturn(Optional.of(oldStudent));

        Student updatedStudent = Student.builder().id(1).firstName(studentDto.firstName())
                .email(studentDto.email())
                .age(studentDto.age()).build();
        //reponi kuzatish


        //ishlatish
        studentService.editStudent(1,studentDto);
        Mockito.when(studentRepo.save(updatedStudent)).thenReturn(updatedStudent);

        //tekshirish
        Mockito.verify(studentRepo,Mockito.times(1)).save(updatedStudent);
    }

    @Test
    void deleteStudent() {

        HttpEntity<?> httpEntity = studentService.deleteStudent(1);
        Assertions.assertEquals(httpEntity.getBody(),"Deleted");
        Mockito.verify(studentRepo,Mockito.times(1)).deleteById(1);
    }

    @Test
    void saveStudent2Test() {
        StudentDto studentDto = new StudentDto("","a@mail.com",12,1);

        NullPointerException ex = assertThrows(NullPointerException.class, () -> {
            studentService.saveStudent2(studentDto);
        });
        Assertions.assertEquals(ex.getMessage(),"Ism bo'sh bo'lmasligi kerak");
    }

    @Test
    void testSaveStudent() {
    }
}
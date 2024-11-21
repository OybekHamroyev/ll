package com.example.testing47.serive.student;

import com.example.testing47.dto.StudentDto;
import org.springframework.http.HttpEntity;

public interface StudentService {
    HttpEntity<?> getStudents();

    HttpEntity<?> saveStudent(StudentDto studentDto);

    HttpEntity<?> saveStudent2(StudentDto studentDto);

    HttpEntity<?> editStudent(Integer id, StudentDto studentDto);

    HttpEntity<?> deleteStudent(Integer id);
}

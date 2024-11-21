package com.example.testing47.controller;

import com.example.testing47.dto.StudentDto;
import com.example.testing47.serive.student.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/student")
@RequiredArgsConstructor
public class StudentController {
    final StudentService studentService;
    @GetMapping
    public HttpEntity<?> getStudents(){
        return studentService.getStudents();
    }
    @PostMapping
    public HttpEntity<?> saveStudent(@RequestBody StudentDto studentDto){
        return studentService.saveStudent(studentDto);
    }
    @PutMapping("/{id}")
    public HttpEntity<?> editStudent(@PathVariable Integer id, @RequestBody StudentDto studentDto){
        return studentService.editStudent(id,studentDto);
    }
    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteStudent(@PathVariable Integer id){
        return studentService.deleteStudent(id);
    }
}

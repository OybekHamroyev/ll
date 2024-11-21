package com.example.testing47.serive.student;

import com.example.testing47.dto.StudentDto;
import com.example.testing47.entity.Student;
import com.example.testing47.repository.GroupRepo;
import com.example.testing47.repository.StudentRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService{
    final StudentRepo studentRepo;
    final GroupRepo groupRepo;
    @Override
    public HttpEntity<?> getStudents() {
        List<Student> all = studentRepo.findAll();
        return ResponseEntity.ok(all);
    }

    @Override
    public HttpEntity<?> saveStudent(StudentDto studentDto) {
        Student student = Student.builder().firstName(studentDto.firstName())
                .email(studentDto.email())
                .age(studentDto.age())
                .build();
        Student save = studentRepo.save(student);
        return ResponseEntity.ok(save);
    }
    @Override
    public HttpEntity<?> saveStudent2(StudentDto studentDto) {
        if (studentDto.firstName()==null ||studentDto.firstName().isEmpty()) throw new NullPointerException("Ism bo'sh bo'lmasligi kerak");
         Student student = Student.builder().firstName(studentDto.firstName())
                .email(studentDto.email())
                .age(studentDto.age())
                 .build();
        Student save = studentRepo.save(student);
        return ResponseEntity.status(201).body(save);
    }
    @Override
    public HttpEntity<?> editStudent(Integer id, StudentDto studentDto) {
        Student student = studentRepo.findById(id).orElseThrow();
        student.setFirstName(studentDto.firstName());
        student.setAge(studentDto.age());
        student.setEmail(studentDto.email());
        increaseAge(student);
        return ResponseEntity.ok(studentRepo.save(student));
    }
    private void increaseAge(Student student){
        student.setAge(student.getAge()+1);
        studentRepo.save(student);
    }
    @Override
    public HttpEntity<?> deleteStudent(Integer id) {
        studentRepo.deleteById(id);
        return ResponseEntity.ok("Deleted");
    }
}

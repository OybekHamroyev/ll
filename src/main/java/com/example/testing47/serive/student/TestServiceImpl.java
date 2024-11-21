package com.example.testing47.serive.student;

import com.example.testing47.dto.StudentDto;
import com.example.testing47.entity.Student;
import com.example.testing47.repository.StudentRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl {
    public int sum(int a, int b){
        return a+b;
    }
}

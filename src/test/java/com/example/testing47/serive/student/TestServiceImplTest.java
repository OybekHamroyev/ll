package com.example.testing47.serive.student;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.junit.jupiter.api.Assertions.*;
@Component
class TestServiceImplTest {

    @Test
    void sumTest() {
        TestServiceImpl testService = new TestServiceImpl();
        int sum = testService.sum(12, 3);
        Assertions.assertEquals(15, sum);

    }
}
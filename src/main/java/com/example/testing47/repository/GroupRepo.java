package com.example.testing47.repository;

import com.example.testing47.entity.Group;
import com.example.testing47.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GroupRepo extends JpaRepository<Group,Integer> {

}

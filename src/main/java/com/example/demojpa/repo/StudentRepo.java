package com.example.demojpa.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demojpa.entity.Student;

public interface StudentRepo extends JpaRepository <Student, Long>{

}

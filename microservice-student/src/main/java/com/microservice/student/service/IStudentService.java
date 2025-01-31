package com.microservice.student.service;

import com.microservice.student.controller.contracts.StudentRequest;
import com.microservice.student.entities.Student;

import java.util.List;

public interface IStudentService {

    List<Student> findAll();
    Student findById(Long id);
    void save(StudentRequest student);
    List<Student> findByCourseId(Long courseId);
}

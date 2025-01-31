package com.microservice.student.service;

import com.microservice.student.controller.contracts.StudentRequest;
import com.microservice.student.entities.Student;
import com.microservice.student.messaging.notifiers.AwsS3EventProducer;
import com.microservice.student.messaging.notifiers.contracts.StudentEvent;
import com.microservice.student.persistence.StudentRepository;

import lombok.AllArgsConstructor;


import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;

@Service
@AllArgsConstructor
public class StudentServiceImpl implements IStudentService {

    
    private final StudentRepository studentRepository;
    private final AwsS3EventProducer awsS3EventProducer;

    @Override
    public List<Student> findAll() {
        return (List<Student>) studentRepository.findAll();
    }

    @Override
    public Student findById(Long id) {
        return studentRepository.findById(id).orElseThrow();
    }

    @Override
    public void save(StudentRequest student) {
        Student student2 = Student.builder()
        .id(student.id())
        .name(student.name())
        .lastName(student.lastName())
        .email(student.email())
        .courseId(student.courseId())
        .fileData(student.fileData().substring(0, 100))
        .build();
        studentRepository.save(student2);
        byte[] imageBytes = Base64.getDecoder().decode(student.fileData());

        StudentEvent awsS3Event = StudentEvent.builder()
        .bucketName("test-mateo")
        .key("test")
        .fileName("test.jpg")
        .fileData(imageBytes)
        .build();
        awsS3EventProducer.sendAwsS3Event(awsS3Event);
    }

    @Override
    public List<Student> findByCourseId(Long courseId) {
        return studentRepository.findAllByCourseId(courseId);
    }
}

package com.microservice.student.controller.contracts;

public record StudentRequest (
     Long id,
     String name,
     String lastName,
     String email,
     String fileData,
     Long courseId
){
    
}

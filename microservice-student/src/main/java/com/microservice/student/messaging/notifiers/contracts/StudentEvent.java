package com.microservice.student.messaging.notifiers.contracts;


import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class StudentEvent {

    private String bucketName;
    private String key;
    private String fileName;
    private byte[] fileData;
    
}

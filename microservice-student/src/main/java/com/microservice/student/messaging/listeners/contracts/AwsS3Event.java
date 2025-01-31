package com.microservice.student.messaging.listeners.contracts;

public record AwsS3Event(
    boolean result ,
    byte[] file
) {

}

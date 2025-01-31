package com.microservice.aws.microservice_aws.messaging.listeners.contracts;

import org.springframework.web.multipart.MultipartFile;

public record StudentEvent(
    String bucketName,
    String key,
    String fileName,
    byte[] fileData
) {

}

package com.microservice.student.messaging.listeners;

import com.microservice.student.messaging.listeners.contracts.AwsS3Event;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;


@Slf4j
@RequiredArgsConstructor
@Component
public class AwsS3Listener {

    @JmsListener(destination = "${messaging.consumers.queues.msvc-student}")
    public void awsS3Listener(AwsS3Event result) {
        log.info("AWS S3 message received: [{}]", result);
    }
}


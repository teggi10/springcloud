package com.microservice.student.messaging.notifiers;

import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import com.microservice.student.messaging.notifiers.contracts.StudentEvent;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AwsS3EventProducer {

    private final JmsTemplate jmsTemplate;
    private final String awsS3TopicName;

    public AwsS3EventProducer(JmsTemplate jmsTemplate, 
                              @Qualifier("aws-s3-topic") String awsS3TopicName) {
        this.jmsTemplate = jmsTemplate;
        this.awsS3TopicName = awsS3TopicName;
    }

    public void sendAwsS3Event(StudentEvent awsS3Event) {
        jmsTemplate.convertAndSend(awsS3TopicName, awsS3Event);
        log.info("Notified aws s3 event: {}", awsS3Event);
    }
}

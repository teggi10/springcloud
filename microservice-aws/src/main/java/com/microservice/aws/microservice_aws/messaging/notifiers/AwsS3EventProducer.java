package com.microservice.aws.microservice_aws.messaging.notifiers;

import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import com.microservice.aws.microservice_aws.messaging.notifiers.contracts.AwsS3Event;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AwsS3EventProducer {

    private final JmsTemplate jmsTemplate;
    private final String msvcStudentTopicName;

    public AwsS3EventProducer(JmsTemplate jmsTemplate, 
                              @Qualifier("student-topic") String msvcStudentTopicName) {
        this.jmsTemplate = jmsTemplate;
        this.msvcStudentTopicName = msvcStudentTopicName;
    }

    public void sendAwsS3Event(AwsS3Event awsS3Event) {
        jmsTemplate.convertAndSend(msvcStudentTopicName, awsS3Event);
        log.info("Notified aws s3 event: {}", awsS3Event);
    }
}

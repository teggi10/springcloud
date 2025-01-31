package com.microservice.student.messaging.configuration;

import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TopicsConfiguration {

    @Value("${messaging.producers.topics.msvc-aws-s3}")
    private String awsS3TopicName;

    @Value("${messaging.producers.topics.msvc-student}")
    private String msvcStudentTopicName;

    @Bean
    @Qualifier("aws-s3-topic")
    public String awsS3TopicName() {
        return awsS3TopicName; 
    }

    @Bean
    @Qualifier("student-topic")
    public String msvcStudentTopicName() {
        return msvcStudentTopicName; 
    }
}


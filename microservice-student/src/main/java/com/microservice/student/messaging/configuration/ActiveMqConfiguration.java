package com.microservice.student.messaging.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.jms.ConnectionFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

import com.microservice.student.messaging.listeners.contracts.AwsS3Event;
import com.microservice.student.messaging.notifiers.contracts.StudentEvent;

import java.util.Map;

@Slf4j
@Configuration
public class ActiveMqConfiguration {

    @Bean
    public JmsListenerContainerFactory<DefaultMessageListenerContainer> myFactory(ConnectionFactory connectionFactory,
                                                                                  DefaultJmsListenerContainerFactoryConfigurer configurer) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setPubSubDomain(true);
        configurer.configure(factory, connectionFactory);
        return factory;
    }

    @Bean
    public MessageConverter jacksonJmsMessageConverter(ObjectMapper objectMapper) {
        log.info("Configuring MessageConverter with the default ObjectMapper provided by Spring Boot");
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setObjectMapper(objectMapper);
        converter.setTargetType(MessageType.TEXT);
        converter.setTypeIdMappings(getMappings());
        converter.setTypeIdPropertyName("_type");
        return converter;
    }

    private Map<String, Class<?>> getMappings() {
        return Map.of(
                AwsS3Event.class.getSimpleName(), AwsS3Event.class,
                StudentEvent.class.getSimpleName(), StudentEvent.class       
                );
    }

}

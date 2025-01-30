package com.microservice.course.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignClientConfig {

    @Bean
    public RequestInterceptor requestInterceptor() {
        return new RequestInterceptor() {
            @Override
            public void apply(RequestTemplate template) {
                // Agrega un encabezado global a todas las solicitudes Feign
                template.header("X-GATEWAY-SECRET", "mateo-123");
            }
        };
    }
}


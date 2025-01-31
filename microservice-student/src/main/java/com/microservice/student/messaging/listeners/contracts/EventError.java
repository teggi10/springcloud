package com.microservice.student.messaging.listeners.contracts;

import lombok.Builder;

@Builder
public record EventError(
        String code,
        String description,
        String name) {

    @Override
    public String toString() {
        return code;
    }

}

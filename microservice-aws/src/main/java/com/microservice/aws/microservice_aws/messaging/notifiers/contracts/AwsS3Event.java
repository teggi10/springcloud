package com.microservice.aws.microservice_aws.messaging.notifiers.contracts;


import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Getter
@Builder
public class AwsS3Event {

    @NonNull
    private boolean result;
    private byte[] file;

}

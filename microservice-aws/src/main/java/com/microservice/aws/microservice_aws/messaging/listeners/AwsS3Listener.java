package com.microservice.aws.microservice_aws.messaging.listeners;

import com.microservice.aws.microservice_aws.service.IS3Service;
import com.microservice.aws.microservice_aws.messaging.listeners.contracts.StudentEvent;
import com.microservice.aws.microservice_aws.messaging.notifiers.contracts.AwsS3Event;
import com.microservice.aws.microservice_aws.messaging.notifiers.AwsS3EventProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.List;


@Slf4j
@RequiredArgsConstructor
@Component
public class AwsS3Listener {

    private final IS3Service s3Service;
    private final AwsS3EventProducer awsS3EventProducer;

    @Value("${spring.destination.folder}")
    private String destinationFolder;

    @JmsListener(destination = "${messaging.consumers.queues.msvc-aws-s3}")
    public void awsS3Listener(StudentEvent result) {
        log.info("AWS S3 message received: [{}]", result);
        try {
            Path staticDir = Paths.get(destinationFolder);
            if (!Files.exists(staticDir)) {
                Files.createDirectories(staticDir);
            }

            Path filePath = staticDir.resolve(result.fileName());
            Path finalPath = Files.write(filePath, result.fileData());

            Boolean uploadResult = this.s3Service.uploadFile(result.bucketName(), result.key(), finalPath);

            AwsS3Event awsS3Event = AwsS3Event.builder()
                    .result(uploadResult)
                    .file(result.fileData())
                    .build();

            awsS3EventProducer.sendAwsS3Event(awsS3Event);
            if (uploadResult) {
                Files.delete(filePath);
                log.info("Archivo cargado correctamente");
            } else {
                log.info("Error al cargar el archivo al bucket");
            }
        } catch (IOException e) {
            log.error("Error al procesar el archivo: {}", e.getMessage(), e);
        }
    }
}


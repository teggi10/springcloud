# Usar una imagen base con JDK
FROM openjdk:17-jdk-slim

# Configurar el directorio de trabajo
WORKDIR /app

# Copiar el archivo JAR generado
COPY target/microservice-course-0.0.1-SNAPSHOT.jar app.jar

# Exponer el puerto del servicio
EXPOSE 9090

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]

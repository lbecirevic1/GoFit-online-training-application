
FROM openjdk:11
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} dietplan-service.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "dietplan-service.jar"]
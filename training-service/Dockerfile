FROM openjdk:11-jre-slim-buster
EXPOSE 8081
ADD target/training-service.jar training-service.jar
ENTRYPOINT ["java", "-jar", "/training-service.jar"]
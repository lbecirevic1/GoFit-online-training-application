FROM openjdk:11-jre-slim-buster
EXPOSE 8083
ADD target/user-service.jar user-service.jar
ENTRYPOINT ["java", "-jar", "/user-service.jar"]
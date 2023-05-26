FROM openjdk:11-jre-slim-buster
EXPOSE 8761
ADD target/eureka-server.jar eureka-server.jar
ENTRYPOINT ["java", "-jar", "/eureka-server.jar"]

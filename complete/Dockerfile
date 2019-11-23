FROM openjdk:8-jdk-alpine
COPY ./target/gs-spring-boot-docker-0.1.0.jar /gs-spring-boot-docker/complete/src/
WORKDIR /gs-spring-boot-docker/complete/src
EXPOSE 8080
CMD ["java", "-jar", "gs-spring-boot-docker-0.1.0.jar"]
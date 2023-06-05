FROM amazoncorretto:17-alpine-jdk
EXPOSE 8081
RUN apk update
ADD target/recipe-app-user-service-0.0.1-SNAPSHOT.jar recipe-app-user-service.jar
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=local-rds", "/recipe-app-user-service.jar"]
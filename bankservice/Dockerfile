FROM openjdk:8-jdk-alpine
EXPOSE 8085
VOLUME /tmp
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
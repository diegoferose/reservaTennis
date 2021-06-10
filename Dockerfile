FROM adoptopenjdk/openjdk11:latest
ARG JAR_FILE=microservicio/build/libs/*.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8084
ENTRYPOINT ["java","-jar","/app.jar"]
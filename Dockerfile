FROM openjdk:17-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} bank-back-app.jar
ENTRYPOINT ["java", "-jar", "/bank-back-app.jar"]
EXPOSE 9090
FROM openjdk:11-jdk-slim
EXPOSE 8080
ARG JAR_FILE=target/contact-manager-app-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} test-app.jar
ENTRYPOINT ["java","-jar","/test-app.jar"]



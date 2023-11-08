FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

    
 WORKDIR /  
ENTRYPOINT ["java", "-jar", "-Xloggc:gc.log", "/app.jar"]
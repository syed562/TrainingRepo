FROM openjdk:17-jdk-slim

WORKDIR /app

# copy the built jar
COPY target/*.jar app.jar

# Expose the same port as server.port of that service
EXPOSE 8080  
ENTRYPOINT ["java","-jar","app.jar"]

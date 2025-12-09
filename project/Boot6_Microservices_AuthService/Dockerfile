FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY target/Boot6_Microservices_AuthService-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8085
ENTRYPOINT ["java","-jar","app.jar"]
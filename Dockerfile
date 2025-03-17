FROM openjdk:21
WORKDIR /app
COPY .env /app/.env
COPY target/springSecurity-0.0.1-SNAPSHOT.jar /app/app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/app.jar"]

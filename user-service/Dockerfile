FROM adoptopenjdk/openjdk11:latest
VOLUME /tmp
COPY target/user-service-1.0.0.jar /app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
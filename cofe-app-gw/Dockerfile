FROM adoptopenjdk/openjdk11:latest
VOLUME /tmp
COPY target/cofe-app-gw-1.0.0.jar /app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
FROM openjdk:8-jdk-alpine
ENV APP_HOME /opt
EXPOSE 8761
COPY target/discovery-server-0.0.1-SNAPSHOT.jar $APP_HOME
ENTRYPOINT ["java", "-jar", "/opt/discovery-server-0.0.1-SNAPSHOT.jar"]
FROM openjdk:8-jdk-alpine
ENV APP_HOME /opt

ARG rabbithost

RUN apk --no-cache add netcat-openbsd
COPY target/User-0.0.1-SNAPSHOT.jar $APP_HOME
COPY User-entrypoint.sh $APP_HOME/User-entrypoint.sh

RUN echo "spring.rabbit.host=$rabbithost">>$APP_HOME/application.properties
RUN chmod 775 $APP_HOME/User-entrypoint.sh
WORKDIR $APP_HOME
ENTRYPOINT ["sh", "-c"]

CMD ["/opt/User-entrypoint.sh"]
EXPOSE 8081
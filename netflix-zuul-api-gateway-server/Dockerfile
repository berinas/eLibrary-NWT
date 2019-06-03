FROM openjdk:8-jdk-alpine
ENV APP_HOME /opt

RUN apk --no-cache add netcat-openbsd
COPY target/api-gateway-0.0.1-SNAPSHOT.jar $APP_HOME
COPY Api-gateway-entrypoint.sh $APP_HOME/Api-gateway-entrypoint.sh
RUN chmod 775 $APP_HOME/Api-gateway-entrypoint.sh
WORKDIR $APP_HOME
ENTRYPOINT ["sh", "-c"]

CMD ["/opt/Api-gateway-entrypoint.sh"]
EXPOSE 8765
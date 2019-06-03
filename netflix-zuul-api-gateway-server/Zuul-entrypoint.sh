#!/bin/sh
while ! nc -z discovery-server 8761 ; do
    echo "Waiting for the Eureka Server"
    sleep 3
done

java -jar /opt/netflix-zuul-api-gateway-server-0.0.1-SNAPSHOT.jar
#!/bin/sh
while ! nc -z discovery-server 8761 ; do
    echo "Waiting for the Eureka Server"
    sleep 3
done

java -jar /opt/api-gateway-0.0.1-SNAPSHOT.jar
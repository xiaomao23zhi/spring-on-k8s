#!/bin/sh

JAVA_OPTS="${JAVA_OPTS} -Dserver.port=${SERVER_PORT}"
JAVA_OPTS="${JAVA_OPTS} -Dspring.profiles.active=${SPRING_PROFILES_ACTIVE}"

exec java ${JAVA_OPTS} -jar spring-on-k8s-0.0.1-SNAPSHOT.jar
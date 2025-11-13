#!/bin/sh

JAVA_OPTS="${JAVA_OPTS} -Dserver.port=${SERVER_PORT}"
JAVA_OPTS="${JAVA_OPTS} -Dspring.profiles.active=${PROFILES_ACTIVE}"

exec java ${JAVA_OPTS} org.springframework.boot.loader.JarLauncher $@
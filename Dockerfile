# syntax=docker/dockerfile:experimental
FROM eclipse-temurin:17-jdk-alpine as builder

WORKDIR /builder

COPY mvnw mvnw
COPY .mvn .mvn
COPY pom.xml pom.xml
COPY src src

RUN --mount=type=cache,target=/home/apps/.m2 ./mvnw package -DskipTests
RUN java -Djarmode=tools -jar target/*.jar extract --layers --destination extracted

FROM eclipse-temurin:17-jre-alpine
VOLUME /tmp

ENV EXTRACTED=/builder/extracted
ENV SERVER_PORT=8080

WORKDIR /workspace/app

RUN addgroup --system apps && adduser --system --ingroup apps apps && \
    mkdir logs && chown apps:apps logs

USER apps

COPY --from=builder ${EXTRACTED}/dependencies/ ./
COPY --from=builder ${EXTRACTED}/spring-boot-loader/ ./
COPY --from=builder ${EXTRACTED}/snapshot-dependencies/ ./
COPY --from=builder ${EXTRACTED}/application/ ./

COPY bin/entrypoint.sh bin/entrypoint.sh

EXPOSE $SERVER_PORT
ENTRYPOINT ["/workspace/app/bin/entrypoint.sh"]

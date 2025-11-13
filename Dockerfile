FROM eclipse-temurin:17-jdk-alpine as builder

WORKDIR /builder

COPY mvnw mvnw
COPY .mvn .mvn
COPY pom.xml pom.xml
COPY src src

RUN --mount=type=cache,target=/home/apps/.m2 ./mvnw package -DskipTests
RUN java -Djarmode=tools -jar target/spring-on-k8s-0.0.1-SNAPSHOT.jar extract --layers --destination extracted

FROM eclipse-temurin:17-jre-alpine

ENV SERVER_PORT=8080

RUN addgroup --system apps && adduser --system --ingroup apps apps && \
    mkdir logs && chown apps:apps logs

WORKDIR /workspace/app

USER apps

COPY --from=builder /builder/extracted/dependencies/ ./
COPY --from=builder /builder/extracted/spring-boot-loader/ ./
COPY --from=builder /builder/extracted/snapshot-dependencies/ ./
COPY --from=builder /builder/extracted/application/ ./

COPY --chown=apps:apps bin/entrypoint.sh bin/entrypoint.sh
RUN chmod +x bin/entrypoint.sh

EXPOSE $SERVER_PORT
ENTRYPOINT ["/workspace/app/bin/entrypoint.sh"]

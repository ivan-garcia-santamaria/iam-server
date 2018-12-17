FROM openjdk:8u171-alpine3.7
RUN apk --no-cache add curl
COPY target/iam-server*.jar iam-server.jar
CMD java ${JAVA_OPTS} -jar iam-server.jar
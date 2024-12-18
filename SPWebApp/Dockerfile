# Build stage
FROM maven:3.9 AS build
WORKDIR /usr/app
COPY . .
RUN mvn install:install-file -Dfile=./lib/colorToproperties-1.1.2.jar -DgroupId=de.androidpit -DartifactId=colorToproperties -Dversion=1.1.2 -Dpackaging=jar
RUN mvn clean package

# Package stage
FROM eclipse-temurin:21-alpine
WORKDIR /usr/app
ARG ACTIVEMQ_HOST=localhost
ENV ACTIVEMQ_HOST=${ACTIVEMQ_HOST}
COPY --from=build /usr/app/target/*.jar app.jar
EXPOSE 8082
ENTRYPOINT java -jar app.jar

# TODO : version as ARG for nicer CI CD and versioning
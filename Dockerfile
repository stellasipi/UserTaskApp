FROM maven:3.8.3-openjdk-16 as builder
RUN mkdir -p /build
RUN mkdir -p /build/logs
WORKDIR /build
COPY pom.xml /build
RUN mvn clean install
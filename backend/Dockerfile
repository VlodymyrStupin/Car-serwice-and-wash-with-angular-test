FROM maven:3.9.0-amazoncorretto-17 AS build
RUN mkdir -p /workspace
WORKDIR /workspace
COPY pom.xml /workspace
COPY src /workspace/src
RUN mvn -f pom.xml clean package -Dmaven.test.skip

FROM openjdk:17-alpine
COPY --from=build /workspace/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]

## Stage 1 - build
#FROM eclipse-temurin:17-jdk-alpine as builder
#WORKDIR /opt/app
#COPY .mvn/ .mvn
#COPY mvnw pom.xml ./
#RUN sed -i 's/\r$//' mvnw
#RUN ./mvnw dependency:go-offline
#COPY ./src ./src
#RUN ./mvnw clean package -Dmaven.test.skip
#CMD ["java", "-jar", "/opt/app/*.jar"]
#
##Stage 2 - run
#FROM eclipse-temurin:17-jre
#WORKDIR /opt/app
#COPY --from=builder /opt/app/target/*.jar  /opt/app/*.jar
#EXPOSE 8080
#ENTRYPOINT ["java", "-jar", "/opt/app/*.jar"]

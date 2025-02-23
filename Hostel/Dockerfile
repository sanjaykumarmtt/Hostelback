FROM maven:3.9.9-eclipse-temurin-21 AS build
# RUN apt-get update
# RUN apt-get install openjdk-21-jdk-y
COPY . .
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-alpine
COPY --from=build /target/*.jar demo.jar
EXPOSE 8080
ENTRYPOINT [ "java","jar","demo.jar" ]
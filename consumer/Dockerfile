FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/consumer-0.0.1-SNAPSHOT.jar consumer.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "consumer.jar"]
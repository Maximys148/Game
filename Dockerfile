FROM openjdk:17-jdk-slim
COPY target/game-0.0.1-SNAPSHOT.jar Game.jar
ENTRYPOINT ["java", "-jar", "Game.jar"]
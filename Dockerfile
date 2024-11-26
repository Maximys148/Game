FROM openjdk:22-rc-jdk-oraclelinux9
EXPOSE 8881
COPY target/game.jar game.jar
ENTRYPOINT ["java", "-jar", "game.jar"]
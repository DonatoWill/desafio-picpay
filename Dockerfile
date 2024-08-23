FROM gradle:8-jdk17-alpine AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build

FROM eclipse-temurin:17.0.5_8-jre-alpine
EXPOSE 8080
COPY --from=build /home/gradle/src/build/libs/wallet.jar /app/
ENTRYPOINT ["java", "-jar", "/app/wallet.jar"]
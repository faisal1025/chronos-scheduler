#---------------BUILD-----------------
FROM eclipse-temurin:17-jdk AS build
WORKDIR /Scheduler
COPY build.gradle settings.gradle ./
COPY gradlew ./
COPY gradle ./gradle
# 3️⃣ Give permission
RUN chmod +x gradlew
RUN ./gradlew dependencies --no-daemon
COPY src ./src
RUN ./gradlew build --no-daemon
#-------------RUN---------------------
FROM eclipse-temurin:17-jdk
WORKDIR /Scheduler
COPY --from=build /Scheduler/build/libs/*.jar scheduler.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "scheduler.jar"]
CMD ["--spring.profiles.active=prod"]

FROM gradle

WORKDIR /garden-land
COPY . .

USER root
RUN chown -R gradle /garden-land
USER gradle
RUN ./gradlew build

CMD ["java", "-jar", "-Dspring.profiles.active=production", "build/libs/garden-land-0.0.1-SNAPSHOT.jar"]
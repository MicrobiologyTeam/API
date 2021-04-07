FROM openjdk
COPY build/libs /usr/src/app
WORKDIR /usr/src/app
RUN bash
CMD ["java", "-jar", "cheeze-api-0.0.1-SNAPSHOT.jar"]
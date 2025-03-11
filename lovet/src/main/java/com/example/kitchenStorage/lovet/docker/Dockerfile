FROM amazoncorretto:21

WORKDIR /app

COPY target/lovet-*.jar app.jar

EXPOSE 8081

CMD ["java", "-jar", "app.jar"]


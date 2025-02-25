FROM amazoncorretto:17-alpine AS build
   WORKDIR /app
   COPY . /app
   RUN ./gradlew bootJar

   FROM amazoncorretto:17-alpine
   WORKDIR /app
   COPY --from=build /app/build/libs/api-0.0.1-SNAPSHOT.jar ./
   EXPOSE 8080
   ENTRYPOINT ["java", "-jar", "api-0.0.1-SNAPSHOT.jar"]
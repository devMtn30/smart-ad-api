FROM amazoncorretto:17-alpine AS build
WORKDIR /app

# gradlew만 먼저 복사해서 실행 권한 부여
COPY gradlew /app/gradlew
RUN chmod +x /app/gradlew

# 나머지 프로젝트 파일 복사
COPY . /app

# Gradle 빌드
RUN ./gradlew bootJar

FROM amazoncorretto:17-alpine
WORKDIR /app

COPY --from=build /app/build/libs/api-0.0.1-SNAPSHOT.jar ./
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "api-0.0.1-SNAPSHOT.jar"]
# 애플리케이션을 컴파일하고 패키징하는 빌드 작업만 수행
FROM eclipse-temurin:21-jdk AS builder

WORKDIR /app

COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .

RUN chmod +x ./gradlew

COPY src src

# Build application
RUN ./gradlew build -x test

# 빌드가 완료된 애플리케이션(JAR 파일)을 더 가벼운 JRE 이미지로 실행하는 작업만 수행
FROM eclipse-temurin:21-jre

WORKDIR /app

# 비루트 사용자 생성
RUN addgroup --system spring && adduser --system --group spring

# Healthcheck를 위해 curl 설치 (비루트 전환 전 설치)
RUN apt-get update \
    && apt-get install -y --no-install-recommends curl \
    && rm -rf /var/lib/apt/lists/*

# server-0.0.1-SNAPSHOT.jar
COPY --from=builder /app/build/libs/*-SNAPSHOT.jar app.jar

# 권한 변경 후 비루트 사용자로 전환
RUN chown -R spring:spring /app
USER spring

EXPOSE 8080

# 실행 프로파일 설정 (필요한 환경변수는 컨테이너 실행 시 주입)
ENV SPRING_PROFILES_ACTIVE=prod

# Healthcheck
HEALTHCHECK --interval=30s --timeout=3s --start-period=40s --retries=3 \
  CMD curl -fsS http://localhost:8080/actuator/health || exit 1

ENTRYPOINT ["java", "-jar", "app.jar"]
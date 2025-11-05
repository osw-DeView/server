FROM eclipse-temurin:21-jdk AS build

WORKDIR /app

ENV TZ=Asia/Seoul

# JAR 파일을 컨테이너로 복사
COPY build/libs/*.jar app.jar

EXPOSE 8080

# 애플리케이션 실행
ENTRYPOINT ["java", "-jar", "app.jar"]


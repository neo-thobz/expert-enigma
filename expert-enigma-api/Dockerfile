FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app

RUN apk add --no-cache maven

COPY pom.xml .
RUN mvn dependency:go-offline -B

COPY src ./src
RUN mvn clean package -DskipTests

EXPOSE 8080

CMD ["java", "-jar", "target/expert-enigma-api-0.0.1-SNAPSHOT.jar"]
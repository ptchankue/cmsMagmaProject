FROM eclipse-temurin:17-jdk

RUN apt-get update && \
    apt-get install -y maven && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*

WORKDIR /app

# Layer cache: dependencies only
COPY pom.xml .
RUN mvn dependency:go-offline -B || true

# Application sources
COPY src ./src

RUN mvn clean package -DskipTests -B

EXPOSE 9082

CMD ["java", "-jar", "/app/target/cmsProject-1.0.4.jar"]

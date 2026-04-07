FROM eclipse-temurin:17-jdk

# Install Maven
RUN apt-get update && \
    apt-get install -y maven curl && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*

#WORKDIR /app

# Copy POM files first for better Docker layer caching
COPY pom.xml .

# Download dependencies (this layer will be cached if POMs don't change)
RUN mvn dependency:go-offline -B || true


RUN mvn clean install -DskipTests


# Set working directory to frontend so Vaadin can find frontend files
#WORKDIR /app

# Expose the port frontend will run on
EXPOSE 9082

# Run the built JAR from the frontend directory (frontend is already built)
CMD ["java", "-jar", "target/cmsProject-1.0.4.jar"]

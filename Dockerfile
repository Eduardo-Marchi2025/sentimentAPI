FROM maven:3.9-eclipse-temurin-21 AS build

WORKDIR /app

COPY pom.xml .

RUN mvn dependency:go-offline

COPY src ./src

# Download ONNX model from GitHub
RUN mkdir -p src/main/resources/models
ADD --chown=root:root https://github.com/SentimentONE/sentimentIA/raw/refs/heads/main/03-models/sentiment_model.onnx \
    src/main/resources/models/sentiment_model.onnx

RUN mvn clean package -DskipTests


# Stage to prepare locales
FROM ubuntu:22.04 AS locale-builder

RUN apt-get update && \
    apt-get install -y --no-install-recommends locales && \
    locale-gen en_US.UTF-8 && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*


FROM gcr.io/distroless/java21-debian12:nonroot

COPY --from=locale-builder /usr/lib/locale/locale-archive /usr/lib/locale/locale-archive
COPY --from=locale-builder /usr/lib/x86_64-linux-gnu/gconv /usr/lib/x86_64-linux-gnu/gconv

# Config locale
ENV LANG=en_US.UTF-8
ENV LANGUAGE=en_US:en
ENV LC_ALL=en_US.UTF-8

WORKDIR /app

COPY --from=build --chown=nonroot:nonroot /app/target/*.jar app.jar

ENTRYPOINT ["java", "-XX:MaxRAMPercentage=80.0", "-jar", "app.jar"]

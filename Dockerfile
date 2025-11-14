# Stage 1: Build
ENTRYPOINT ["java", "-Xmx512m", "-Xms256m", "-jar", "app.jar"]
# Executar aplicação

ENV SPRING_PROFILES_ACTIVE=prod
# Variáveis de ambiente padrão

EXPOSE 8080
# Expor porta

COPY --from=build /app/target/*.jar app.jar
# Copiar JAR do stage de build

USER spring:spring
RUN addgroup -S spring && adduser -S spring -G spring
# Criar usuário não-root

WORKDIR /app
FROM eclipse-temurin:17-jre-alpine
# Stage 2: Run

RUN mvn clean package -DskipTests
COPY src ./src
# Copiar código fonte e build

RUN mvn dependency:go-offline -B
COPY pom.xml .
# Copiar apenas pom.xml primeiro para cache de dependências

WORKDIR /app
FROM maven:3.9.9-eclipse-temurin-17-alpine AS build


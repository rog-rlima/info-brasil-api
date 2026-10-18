# ==============================================================================
# ETAPA 1: Compilação usando o Java 21 (Mudei aqui)
# ==============================================================================
FROM eclipse-temurin:21-jdk-alpine AS build

WORKDIR /app

# Copia os arquivos de configuração do Maven Wrapper
COPY .mvn/ .mvn
COPY mvnw pom.xml ./

# Corrige as permissões de execução do script no Linux Alpine
RUN chmod +x mvnw

# Baixa as dependências em uma camada isolada
RUN ./mvnw dependency:go-offline

# Copia o código fonte e compila o projeto
COPY src ./src
RUN ./mvnw clean package -DskipTests

# ==============================================================================
# ETAPA 2: Imagem final leve para execução usando Java 21 (Mudei aqui também)
# ==============================================================================
FROM eclipse-temurin:21-jre-alpine

RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]

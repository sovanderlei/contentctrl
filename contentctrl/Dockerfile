# Usar a imagem oficial do OpenJDK 17 como base
FROM openjdk:17-jdk-slim AS build

# Definir o diretório de trabalho
WORKDIR /app

# Copiar o JAR gerado pelo Maven
COPY target/contentctrl-0.0.1-SNAPSHOT.jar app.jar

# Expor a porta 8080, que é a que o Spring Boot usa por padrão
EXPOSE 8080

# Comando para rodar a aplicação Spring Boot
ENTRYPOINT ["java", "-jar", "/app/app.jar"]

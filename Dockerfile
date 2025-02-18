# Use uma imagem base do OpenJDK
FROM openjdk:17-jdk-alpine

# Defina o diretório de trabalho no contêiner
WORKDIR /app

# Copie o arquivo JAR do seu projeto para o contêiner
COPY target/SkillForge-0.0.1-SNAPSHOT.jar app.jar

# Exponha a porta que a aplicação irá rodar
EXPOSE 8080

# Comando para executar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
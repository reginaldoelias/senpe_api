# Etapa 1: Build da aplicação com Maven
FROM maven:3.9.6-eclipse-temurin-17 AS build

WORKDIR /app

# Copiar arquivos de configuração e código
COPY pom.xml .
COPY src ./src

# Compilar e gerar o WAR
RUN mvn clean package -DskipTests

# Etapa 2: Deploy no Tomcat
FROM tomcat:10.1-jdk17

# Remover apps padrão do Tomcat
RUN rm -rf /usr/local/tomcat/webapps/*

# Copiar o WAR gerado para o Tomcat
COPY --from=build /app/target/*.war /usr/local/tomcat/webapps/senpe.war

EXPOSE 8080

CMD ["catalina.sh", "run"]




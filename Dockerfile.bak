# Imagen base con JDK 17
FROM eclipse-temurin:17-jdk-alpine

# Directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiar el jar generado
COPY target/productapi-0.0.1-SNAPSHOT.jar app.jar

# Puerto que va a exponer el contenedor
EXPOSE 8080

# Comando para ejecutar el JAR
ENTRYPOINT ["java", "-jar", "app.jar"]

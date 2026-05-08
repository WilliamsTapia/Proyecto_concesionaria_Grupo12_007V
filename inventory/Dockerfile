# Usamos Eclipse Temurin, que es más estable y moderno que el antiguo openjdk
FROM eclipse-temurin:17-jdk-focal

# Definimos dónde está el archivo .jar
ARG JAR_FILE=target/*.jar

# Lo copiamos al contenedor
COPY ${JAR_FILE} app.jar

# Ejecutamos la aplicación
ENTRYPOINT ["java", "-jar", "/app.jar"]
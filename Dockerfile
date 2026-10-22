FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY target/*.jar app.jar
CMD ["sh", "-c", "java -jar app.jar 10 && while true; do sleep 30; done"]

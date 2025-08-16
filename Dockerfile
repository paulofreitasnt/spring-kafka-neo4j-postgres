FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY target/*.jar app.jar
ADD https://raw.githubusercontent.com/vishnubob/wait-for-it/master/wait-for-it.sh wait-for-it.sh
RUN chmod +x wait-for-it.sh
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
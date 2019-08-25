FROM openjdk:8-jre-alpine
COPY target/gresmer-school-crud-0.0.1-SNAPSHOT.jar gresmer-school-crud-0.0.1-SNAPSHOT.jar
ARG key
ARG secret
ENV aws_user_key=$key
ENV aws_user_secret_key=$secret
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "gresmer-school-crud-0.0.1-SNAPSHOT.jar"]
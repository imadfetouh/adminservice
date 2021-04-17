FROM openjdk:11
EXPOSE 8085
ARG JAR_FILE=/adminservice/target/adminservice-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
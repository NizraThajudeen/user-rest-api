FROM openjdk:17
LABEL maintainer="Nizra"
ADD target/test-0.0.1-SNAPSHOT.jar springboot-rest-api.jar.jar
ENTRYPOINT ["java", "-jar", "springboot-rest-api.jar"]

# FROM openjdk:17-oracle
# COPY target/*.jar user-restapi.jar
# EXPOSE

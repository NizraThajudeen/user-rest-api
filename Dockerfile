FROM openjdk:17
LABEL maintainer="Nizra"
ADD target/test-0.0.1-SNAPSHOT.jar springboot-rest-api.jar
ENTRYPOINT ["java", "-jar", "springboot-rest-api.jar"]
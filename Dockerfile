FROM openjdk:17
LABEL maintainer="Nizra"
ADD target/user-restapi-0.0.1-SNAPSHOT.jar user-restapi.jar
ENTRYPOINT ["java", "-jar", "user-restapi.jar"]
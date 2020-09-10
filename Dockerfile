FROM openjdk:11
COPY ./target/spring-craigslist-clone-0.0.1-SNAPSHOT.jar /opt/app.jar
WORKDIR /opt
CMD ["java", "-jar", "./app.jar"]
EXPOSE 8080/tcp
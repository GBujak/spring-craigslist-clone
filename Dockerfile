FROM openjdk:11-jdk
VOLUME /tmp
RUN groupadd spring && useradd -m -g spring -s /bin/sh spring
USER spring:spring
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} application.jar
ENTRYPOINT ["java","-jar", "application.jar"]
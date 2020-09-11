FROM openjdk:11-jdk
VOLUME /tmp
RUN groupadd spring && useradd -m -g spring -s /bin/sh spring
RUN mkdir /tmp/image_upload
RUN chown spring:spring /tmp/image_upload
RUN chmod 777 /tmp/image_upload
USER spring:spring
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} application.jar
ENTRYPOINT ["java","-jar", "application.jar"]
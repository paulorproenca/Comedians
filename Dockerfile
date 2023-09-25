FROM amazoncorretto:11
COPY nonrest/target/nonrest.jar nonrest.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/nonrest.jar"]
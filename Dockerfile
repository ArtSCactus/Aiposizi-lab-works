FROM openjdk:14.0.1-oracle
LABEL maintainer="artemir14@gmail.com"
COPY target/*.jar university-rest.jar
EXPOSE 5432
EXPOSE 8081
CMD [ "java","-jar","university-rest.jar"]
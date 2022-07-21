FROM openjdk:8-alpine
MAINTAINER Your Name <you@example.com>

ADD target/consulta-ibge-0.0.1-SNAPSHOT-standalone.jar /consulta-ibge/app.jar

EXPOSE 8080

CMD ["java", "-jar", "/consulta-ibge/app.jar"]

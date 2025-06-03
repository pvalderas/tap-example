FROM arm64v8/eclipse-temurin:21
RUN mkdir /opt/app
COPY celinglight-0.0.1-SNAPSHOT.jar /opt/app
CMD ["java", "-jar", "/opt/app/celinglight-0.0.1-SNAPSHOT.jar"]
EXPOSE 8080
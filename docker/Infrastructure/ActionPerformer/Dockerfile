FROM arm64v8/eclipse-temurin:8-jammy
RUN mkdir /opt/app
COPY actionperformer-1.0.jar /opt/app
CMD ["java", "-jar", "/opt/app/actionperformer-1.0.jar"]
EXPOSE 9090
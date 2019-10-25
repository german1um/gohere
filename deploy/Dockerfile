FROM java:8

COPY gohere.jar /app/init.jar
COPY .gcs-credentials /app/
WORKDIR /app

CMD ["java", "-jar", "-Dspring.profiles.active=prod", "init.jar"]
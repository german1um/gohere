FROM java:8

COPY gohere.jar /app/init.jar
WORKDIR /app

CMD ["java", "-jar", "-Dspring.profiles.active=prod", "init.jar"]
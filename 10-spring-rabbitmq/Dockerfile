FROM openjdk:17 as rabbitmq
EXPOSE 9002
WORKDIR /app

# Copy maven executable to the image
COPY mvnw .
COPY .mvn .mvn

# Copy the pom.xml file
COPY pom.xml .

# Copy the project source
COPY ./src ./src
COPY ./pom.xml ./pom.xml

RUN chmod 755 /app/mvnw

RUN ./mvnw package -DskipTests
ENTRYPOINT ["java","-jar","target/10-spring-rabbitmq-1.0-SNAPSHOT.jar"]
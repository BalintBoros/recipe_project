# Step 1: Specify the base image
FROM openjdk:17-alpine as build

# Step 2: Set the working directory
WORKDIR /workspace/app

# Step 3: Copy the Maven pom.xml and download dependencies
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
RUN ./mvnw dependency:go-offline

# Step 4: Copy the rest of the project and build it
COPY src src
RUN ./mvnw package -DskipTests
RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)

# Step 5: Specify the entry point
ENTRYPOINT ["java","-jar","/app.jar"]
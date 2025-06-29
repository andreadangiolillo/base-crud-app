#Build
FROM amazoncorretto:21 AS build

RUN yum update -y && yum install -y tar gzip

WORKDIR /app

COPY . .
RUN chmod +x mvnw
RUN sed -i 's/\r$//' mvnw && chmod +x mvnw
RUN ./mvnw clean package -DskipTests


# Runtime
FROM amazoncorretto:21

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]

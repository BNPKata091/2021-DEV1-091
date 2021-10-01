# **kata-dev-books** project
This project uses Spring Boot.

If you want to learn more about Springboot, please visit its website: https://spring.io/projects/spring-boot .

# Getting Started

1.	`git clone https://github.com/BNPKata091/2021-DEV1-091.git `
2.	To build and run the project, you will need a jdk 11+ and maven 3.2+

# Build and Test

## Running the application

You can run the application using:
```
mvn spring-boot:run
```

## Testing the application

Once the application runs, you can test it using curl or postman or the tool of your choice by making a POST request to 
```
http://localhost:8080/books/cost
```
An example of a valid JSON body request is available in exampleRequest.json, at the root of the project.

## Packaging the application

The application can be packaged using:
```
mvn package
```

It produces the `kata-dev-books-0.0.1-SNAPSHOT.jar` file in the `/target` directory.

The application is now runnable using `java -jar target/kata-dev-books-0.0.1-SNAPSHOT.jar`.

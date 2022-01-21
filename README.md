# Welcome
Welcome to ATEXO Java backend challenge.

* This project is backend part of the challenge you can find the frontend in the other project in ZIP file named challenge-ATEXO-front-card-game
* Setting up the project requires Java 11 and IntelliJ (https://www.jetbrains.com/idea/download), Eclipse (https://www.eclipse.org/downloads/) or Visual studio code (https://code.visualstudio.com/download).
* This project was generated with [Spring CLI](https://start.spring.io/).

## Running the project

The project builds and runs. You can start it by typing `./mvnw spring-boot:run` in the console. In IDE, you can run it by creating a new "maven run configuration" for the Maven project "challenge-ATEXO-front-card-game" with the command `spring-boot:run`. This will allow you to use breakpoints for debugging.

The command `./mvnw clean test` will clean compile the project and run the unit tests.

## Use the endpoints

The project expose 4 endpoints as API, we can test 

* curl -X GET localhost:8080/cards/distributes/{number of cards we want}
* curl -X GET localhost:8080/cards/order-by-color
* curl -X GET localhost:8080/cards/order-by-value
* curl -X DELETE localhost:8080/cards/clear-hand

Enjoy :) 


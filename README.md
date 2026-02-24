GoPass

GoPass is a backend-based application developed to manage travel and route planning operations through a centralized system.
The project focuses on structured data management, relational database design, and service-based backend architecture.

The system enables users to manage routes, locations, and transportation-related data efficiently through RESTful services.

Project Scope

The application provides:

Route and location management

City and transportation data handling

Structured database operations

CRUD-based service endpoints

Centralized data access management

Technologies

Java

Spring Boot

Spring Data JPA

RESTful API

Maven

Relational Database System

Architecture

The project follows a layered architecture approach:

src
 ├── controller
 ├── service
 ├── repository
 ├── entity
 └── config

Application layers are separated to improve maintainability and scalability.

Database Design

The system is built on a relational database structure where entities such as routes, cities, and related transportation data are managed through mapped relationships.

JPA is used for object-relational mapping and database interaction.

Setup

Clone the repository:

git clone https://github.com/USERNAME/GoPass.git

Navigate to the project directory:

cd GoPass

Run the application:

mvn spring-boot:run

The application runs on:

http://localhost:8080


Development Team

Nisa Yakar

Sinem Yaman

Mert Pepele



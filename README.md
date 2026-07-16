# Spring Boot Demo API

A simple REST API built with:

- Java 21
- Spring Boot
- Spring Data JPA
- H2 Database
- Maven

## Features

- Complete user CRUD REST API
- Request validation and consistent error responses
- JPA/Hibernate
- Entity mapping
- Repository pattern
- Service layer and dependency injection
- Integration tests

## API

| Method | Endpoint | Description |
| --- | --- | --- |
| `GET` | `/users` | List users |
| `GET` | `/users/{id}` | Get a user |
| `POST` | `/users` | Create a user |
| `PUT` | `/users/{id}` | Update a user |
| `DELETE` | `/users/{id}` | Delete a user |

Create or update requests use this shape:

```json
{
  "name": "Ana",
  "email": "ana@example.com"
}
```

## Technologies

- Java 21
- Spring Boot
- Spring Data JPA
- H2
- Maven

## Run

```bash
mvn spring-boot:run
```

## Test

```bash
mvn test
```

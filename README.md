# QUARKUS User REST API

A simple CRUD-based RESTful API built with **QUARKUS** to make create, get and delete operations in a Postgres DB
This project serve as skill a test for Stiko DX

---

## Technologies Used

- **Java 21** 
- **QUARKUS framework**
- **Docker**
- **Postgres DB**
- **Maven** 

## Prerequisites

- **Java**
- **Maven**
- **Docker**
- **Git**

---

## Getting Started

### 1. Clone the repository

```bash
git clone https://github.com/RuiPinh0/quarkus-user-crud.git
cd quarkus-user-crud
```

### 2. Build the project

```bash
mvn package
```

All unit tests will run automatically using an H2 in-memory database.

### 3. Start the application with Docker Compose

```bash
docker compose up --build
```

This will start both the PostgreSQL database and the Quarkus application.

### 4. Access the API

The API will be available at:  
[http://localhost:8080](http://localhost:8080)

---

## API Endpoints

| Method | Endpoint         | Description           |
|--------|------------------|----------------------|
| POST   | `/user`          | Create a new user    |
| GET    | `/user/{id}`     | Get user by ID       |
| DELETE | `/user/{id}`     | Delete user by ID    |

## API POST body example 

```json
{
  "username": "johndoe",
  "firstName": "John",
  "lastName": "Doe",
  "email": "john.doe@example.com",
  "phone": "123456789",
  "address": "123 Main St"
}
```
Note that "username", "firstName", "lastName" and "email" are all mandatory fields, and "email most be unique".
That means sending a request with any of those fields without a value (or repeated in email's case) will result in a http 400 error code.

## API Curl examples 

POST example 
```bash
curl --location 'http://localhost:8080/user/' \
--header 'Content-Type: application/json' \
--data-raw '{
  "username": "johndoe",
  "firstName": "John",
  "lastName": "Doe",
  "email": "john.doe@example.com",
  "phone": "123456789",
  "address": "123 Main St"
}'
```

GET example 
```bash
curl --location 'http://localhost:8080/user/1'
```

DELLETE example 
```bash
curl --location --request DELETE 'http://localhost:8080/user/1'
```
---

## Configuration

The main configuration is in `src/main/resources/application.properties`.  
For local development, the default profile uses PostgreSQL.  
For tests, an H2 in-memory database is used.

---

## Running Locally

To run:

```bash
mvn quarkus:dev
```
you can replace 'dev' for 'prod'. 
And BE SURE you fix the DB connections in properties file before run.

## Running Tests

To run all tests:

```bash
mvn test
```
or

```bash
mvn quarkus:test
```

---

## OpenAPI and Swagger UI

- OpenAPI specification is available at: [http://localhost:8080/q/openapi](http://localhost:8080/q/openapi)
- Swagger UI is available at: [http://localhost:8080/q/swagger-ui](http://localhost:8080/q/swagger-ui)

---

## Notes

- The project uses DTOs and validation for input data.
- All endpoints return appropriate HTTP status codes for errors (e.g., 400 for bad requests, 404 for not found).
- The code follows SOLID principles and is structured for maintainability and extensibility.

---

## License

This project is for demonstration and skill assessment purposes.
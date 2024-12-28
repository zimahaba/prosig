# Prosig Blog Platform

## Requirements
- JDK 21
- Docker / Docker Compose

## Running

### Database Container
Add database environment variables:
```
export DB_HOST=localhost
export DB_PORT=5432
export DB_NAME=blog
export DB_USERNAME=prosig
export DB_PASSWORD=prosig
```

Start up the database container:
```
docker compose up -d
```
This command may be different depending on your docker compose version.

### Application
Run the application:
```
./gradlew bootRun
```
When the application starts the migration scripts will be executed to create the database tables.
The repository has a Postman collection file that can be used to test the endpoints.

### Tests

Running the unit tests:
```
./gradlew clean test
```

Running the integration tests:
```
./gradlew clean integrationTest
```

# Next Steps

- Finish the integration tests.
- Validate mandatory fields on requests and do other validations if necessary.
- Configure logging and add MDC library.
- Update the initial script to have two different database users, one with DDL permissions to use on the migration, and another one with DML and DQL permissions to use on the application.
- Add users and authentication to the application.
- Add security configuration like authenticated endpoints and CORS.

In addition to the specified requirements, it would be necessary to consider the infrastructure and deployment aspects of the application. Some important points, among others, would be:
- Where would the application be deployed? Is worth to go cloud? Would a bash script need to be created? Would Docker/Docker Compose be used, or would Kubernetes deployment be required?
- Is using a relational database the best option for this type of application? Would a non-relational database be a better fit?
- How many users are expected for the application? What is the expected request rate for the application? Will the application need to scale?
- What environments are required for deploying the application? Dev, stage, and prod?
- How would the application's pipeline work, and which tool would be used to automate builds, tests, and deployment?
- Should we continue with a monolithic application, or would it be necessary to split it into microservices?
- What client will access the application? Will the API be public or accessible only to internal clients?
- How would application observability be handled? Where would the logs be sent? What metrics need to be monitored? What alerts are necessary?

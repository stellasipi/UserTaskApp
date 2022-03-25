# UserTaskApp

## About The Project

The application manages users with their tasks. The API allows you to create, list and update users, then you can also create, update, list and delete their task(s). Moreover after every 5 minutes the application checks if there are any  tasks with expired deadline and Pending status, if yes their status sets to Done.

## Built With:

- Java 16
- SpringBoot
- Maven
- database: PostgreSQL
- database migration: FlyWay
- containerization: Docker (production-only)

## Getting Started
If you plan to run the tests and/or the application locally, first you need to create a local PostgreSQL schema, with the following:
- **schema name:** usertaskdb
- **username:** usertask_user
- **password:** usertask_password

### Only testing

```sh
mvn test -Ptest
```

### Local developement environment

1. ```sh
    mvn clean install -Pdev
    ```
   The tests will run with the command above

2. ```sh
    mvn spring-boot:run -Dspring-boot.run.profiles=dev
    ```
3. Now you can run the curl commands.

### Production environment

1. ```sh
    docker-compose build
    ```

2. ```sh
    docker-compose up
    ```
3. Now you can run the curl commands.

## Usage (curl commands)

### User related commands

#### Create user
```sh
curl -i -H "Content-Type: application/json" -X POST -d '{"username":"jsmith","first_name" : "John", "last_name" : "Smith"}'http://localhost:8080/api/user
```

#### Update user
```
curl -i -H "Content-Type: application/json" -X PUT -d '{"first_name" : "John", "last_name" : "Doe"}' http://localhost:8080/api/user/{id}
```

#### List all users
```sh
curl -i -H "Accept: application/json" -H "Content-Type: application/json" -X GET http://localhost:8080/api/user
```

#### Get User info
```sh
curl -i -H "Accept: application/json" -H "Content-Type: application/json" -X GET http://localhost:8080/api/user/{id}
```

### Task related commands

#### Create Task
```sh
curl -i -H "Content-Type: application/json" -X POST -d '{"name":"My task","description" : "Description of task", "date_time" : "2016-05-25 14:25:00"}' http://localhost:8080/api/user/{user_id}/task
```

#### Update Task
```sh
curl -i -H "Content-Type: application/json" -X PUT -d '{"name":"My updated task"}' http://localhost:8080/api/user/{user_id}/task/{task_id}
```

#### Delete Task
```sh
curl -i -H "Content-Type: application/json" -X DELETE http://localhost:8080/api/user/{user_id}/task/{task_id}
```

#### Get Task Info
```sh
curl -i -H "Accept: application/json" -H "Content-Type: application/json" -X GET http://localhost:8080/api/user/{user_id}/task/{task_id}
```

#### List all tasks for a user

```sh
curl -i -H "Accept: application/json" -H "Content-Type: application/json" -X GET http://localhost:8080/api/user/{user_id}/task
```
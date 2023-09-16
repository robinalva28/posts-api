# posts-api
A SpringBoot 3.0.1 app, that provides some GET endpoints to request posts and its comments. 
This project consumes a REST API from https://jsonplaceholder.typicode.com/ at startup and stores the data in a postgres DB.
The postgresDB is running in a isolated docker container.

You must have Docker daemon running in your machine to run this project in local environment.

## Run Docker DB image

##### 1- Go to root folder and run ```docker compose up```

## Run app

##### 1- ```mvn spring-boot:run```


# cofe-app

## Microservices

* 	[cofe-app-gw](https://github.com/cengizbursali/cofe-app/tree/master/cofe-app-gw/) - Open-Source Relational Database Management System
* 	[user-service](https://github.com/cengizbursali/cofe-app/tree/master/user-service/) - Open-Source Relational Database Management System
* 	[reward-service](https://github.com/cengizbursali/cofe-app/tree/master/reward-service/) - Open-Source Relational Database Management System


## Tech stack & Open-source libraries

### Data

* 	[PostgreSql](https://www.postgresql.org/) - Open-Source Relational Database Management System

### Server - Backend

* 	[JDK-11](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html) - Java™ Platform, Standard Edition Development Kit
* 	[Spring Boot](https://spring.io/projects/spring-boot) - Framework to ease the bootstrapping and development of new Spring Applications
* 	[Maven](https://maven.apache.org/) - Dependency Management

###  Libraries and Plugins

* 	[Lombok](https://projectlombok.org/) - Never write another getter or equals method again, with one annotation your class has a fully featured builder, Automate your logging variables, and much more.
* 	[Swagger](https://swagger.io/) - Open-Source software framework backed by a large ecosystem of tools that helps developers design, build, document, and consume RESTful Web services.

### Others 

* 	[Git](https://git-scm.com/) - Free and Open-Source distributed version control system


## Running the application locally

*	use **docker-compose up -d** to run the dockerized spring boot project

## Documentation

* 	[Swagger](http://localhost:8083/swagger-ui.html) - `http://localhost:8083/swagger-ui.html`- Documentation & Testing

### URLs

|                   URL                   | Method |          Remarks       |
|-----------------------------------------|--------|------------------------|
|`https://localhost:8083/users`           | GET    | Get All Users          |
|`https://localhost:8083/users/1`         | GET    | Get A User             |
|`https://localhost:8083/users`           | POST   | Create A User          |
|`https://localhost:8083/rewards`         | GET    | Get All Rewards        |
|`https://localhost:8083/rewards/1`       | GET    | Get A Reward           |
|`https://localhost:8083/rewards`         | POST   | Create A Reward        |
|`https://localhost:8083/user-rewards`    | POST   | Assing Reward to User  |



## EER Diagram






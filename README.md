# cofe-app

### Microservices & Database

* 	[cofe-app-gw](https://github.com/cengizbursali/cofe-app/tree/master/cofe-app-gw/) - to communicate with all the services
* 	[user-service](https://github.com/cengizbursali/cofe-app/tree/master/user-service/) - to connect database for providing rest service
* 	[reward-service](https://github.com/cengizbursali/cofe-app/tree/master/reward-service/) - to connect database for providing rest service

* 	[postgresql-db](https://github.com/cengizbursali/cofe-app/tree/master/postgresql-db/) - to store client information


### Tech stack & Open-source libraries

#### Server - Backend

* 	[JDK-11](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html) - Java™ Platform, Standard Edition Development Kit
* 	[Spring Boot](https://spring.io/projects/spring-boot) - Framework to ease the bootstrapping and development of new Spring Applications
* 	[Maven](https://maven.apache.org/) - Dependency Management

#### Data

* 	[PostgreSql](https://www.postgresql.org/) - Open-Source Relational Database Management System

####  Libraries and Plugins

* 	[Lombok](https://projectlombok.org/) - Never write another getter or equals method again, with one annotation your class has a fully featured builder, Automate your logging variables, and much more.
* 	[Swagger](https://swagger.io/) - Open-Source software framework backed by a large ecosystem of tools that helps developers design, build, document, and consume RESTful Web services.

#### Others 

* 	[Git](https://git-scm.com/) - Free and Open-Source distributed version control system
*   [Docker](https://www.docker.com/) - A set of platform as a service products that use OS-level virtualization to deliver software in packages called containers.



### Running the application

*	use "**docker-compose up -d**" to run the dockerized spring boot projects

### Documentation

* 	[Swagger](http://localhost:8083/swagger-ui.html) - `http://localhost:8083/swagger-ui.html`- Documentation & Testing

### URLs

|                   URL                   | Method |          Remarks       |
|-----------------------------------------|--------|------------------------|
|`https://localhost:8083/users`           | GET    | get all users          |
|`https://localhost:8083/users/1`         | GET    | get a user             |
|`https://localhost:8083/users`           | POST   | create a user          |
|`https://localhost:8083/rewards`         | GET    | get all rewards        |
|`https://localhost:8083/rewards/1`       | GET    | get a reward           |
|`https://localhost:8083/rewards`         | POST   | create a reward        |
|`https://localhost:8083/user-rewards`    | POST   | assing reward to user  |


### EER Diagram

<img src="images\eer_diagram.png"/>

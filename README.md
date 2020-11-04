# cofe-app

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

* 	[git](https://git-scm.com/) - Free and Open-Source distributed version control system


## Running the application locally

*	Default active profile is **`test`**. When the application is running, **Flyway** will create the necessary tables and system data along with sample data. In the **`test`** profile, the application uses **H2** database (data in memory).


### URLs

|                   URL                   | Method |          Remarks       |
|-----------------------------------------|--------|------------------------|
|`https://localhost:8080/index`           | GET    | Home Page              |
|`https://localhost:8080/sbat/index`      | GET    | Home Page              |
|`https://localhost:8080/sbat/about`      | GET    | About Page             |
|`https://localhost:8080/sbat/tech-stack` | GET    | Technology Stack Table |
|`https://localhost:8080/sbat/close`      | GET    | Close App via Actuator |
|`https://localhost:8080/sbat/login`      | GET    | Login Page             |
|`https://localhost:8080/sbat/error`      | GET    | Custom Error Page      |

## Documentation

* 	[Swagger](http://localhost:8083/swagger-ui.html) - `http://localhost:8080/swagger-ui.html`- Documentation & Testing

## EER Diagram

<img src="images\SBAT-EER-Diagram.png"/>

# Employees RestFull Project by Camilo Díaz Jaimes
# Api-docs: https://api-documentation-61.api-docs.io/1.0/employees

> employees-tech-exam

[![gradle](https://img.shields.io/badge/gradle-v5.0.X-yellow.svg)](https://gradle.org/install/)

> Project CRUD Restfull model about Employee Entity

## Prerequisites

You will need the following services properly installed on your computer.

* [Git](http://git-scm.com/)
* [Gradle](https://gradle.org)
* [Maven](https://maven.apache.org/)
* [Docker](https://www.docker.com/)
* [Docker-compose](https://docs.docker.com/compose/install/)

## Installation

You need [install Gradle](http://www.gradle.org/installation) or use a [Gradle wrapper](http://www.gradle.org/docs/current/userguide/gradle_wrapper.html) inside this project.

* Execute: `git clone https://github.com/camilodiazj/employees-project.git`.
* Change into the new directory `cd employees-project`

## Build project

```bash
./gradlew clean build
```

## Run tests

```bash
./gradlew clean test
```

## Run
You need a Database to persist Data
* To deploy Postgres Db:
* Change into `cd db-on-docker`
```bash
docker-compose up
```
Run Application from base file `employees-project`
```bash
gradlew clean build

java -jar build/libs/employees-tech-exam-0.0.1-SNAPSHOT.jar
```

## Error responses catalog

* EmployeesError001: Error in Business data provided
* EmployeesError998: Error in service transactions
* EmployeesError999: Unexpected Error


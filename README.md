# employees-tech-exam By Camilo Díaz Jaimes

> employees-tech-exam

[![gradle](https://img.shields.io/badge/gradle-v5.0.X-yellow.svg)](https://gradle.org/install/)

> Project CRUD Restfull model about Employee Entity

## Prerequisites

You will need the following services properly installed on your computer.

* [Git](http://git-scm.com/)
* [Gradle](https://gradle.org)
* [Maven](https://maven.apache.org/)

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

```bash
java -jar build/libs/bpop-reliefs-backend-core-*.jar
TODO: Database
```

## Error responses catalog

* FinancialReliefs01: Customer not found
* FinancialReliefs02: Customer has no active payroll loans
* FinancialReliefs03: Customer has no active accounts


# Project of Network Programming

## ************ WORK IN PROCESS ************

This repository includes Sanskar Gupta's and Felix Seifert's project of the course Network Programming at KTH Royal 
Institute of Technology. The project is a small microservices-based application (MSA) which is based on the 
[Quarkus framework](https://quarkus.io/).

## Infrastructure

As first step, you would have to start the infrastructure. This can be achieved by running the `docker-compose.yml` 
file.

```bash
docker-compose up
```

The infrastructure consists out of a PostgreSQL database container and a Keycloak identity and access management 
application.

According to the database per service pattern, each service receives its own database within the PostgreSQL container. 
The credentials of the databases for the different services can be seen in the 
[init-databases.sql](infrastructure/postgres/init-databases.sql) file.

The Keycloak application is automatically initialised with the settings from the file 
[realm-network-programming-project.json](infrastructure/keycloak/realm-network-programming-project.json). Currently, 
the whole project has the roles `USER` and `ADMIN`. Appropriate users are `alex` with the password `alex` who has the 
role `USER` and `admin` with the password `admin` who has the role `ADMIN`.

## Microservices

The microservices of the MSA can currently be started only separately. For starting a service, go to its directory and 
start it either like a normal Java application, in the Quarkus development mode or compile it to a native executable.
For more information on how to run a Quarkus application, please consult the 
[Quarkus page about Maven tooling](https://quarkus.io/guides/maven-tooling.html).

## Test

To test the identity framework with its authentication and authorisation, start the infrastructure and start the 
service [visited-places](visited-places). Then, query Keycloak to receive a valid access token (and store it for 
simplicity).

```bash
export access_token=$( \
    curl -X POST http://localhost:8180/auth/realms/network-programming-project/protocol/openid-connect/token \
    --user visited:visited \
    -H 'content-type: application/x-www-form-urlencoded' \
    -d 'username=alex&password=alex&grant_type=password' | jq --raw-output '.access_token' \
 )
```

Use this token then as a `Bearer` token to query the `visited-places` service.

```bash
curl -v -X GET \
  http://localhost:8081/api/v1/places \
  -H "Authorization: Bearer "$access_token
```

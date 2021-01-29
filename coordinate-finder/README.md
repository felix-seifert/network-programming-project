# `coordinate-finder`

This microservice uses the framework Quarkus. The following explanations tell you how to start the microservice.

## Running in dev mode

You can run the microservice in dev mode which enables live coding using:

```shell script
./mvnw compile quarkus:dev
```

## Packaging and running as normal Java application

The microservice can be packaged using:

```shell script
./mvnw package
```

The command produces the `coordinate-finder-<version>-runner.jar` file in the `/target` directory. Be aware that this is 
not an _über-jar_ as the dependencies are copied into the `target/lib` directory.

If you want to build an _über-jar_, execute the following command:

```shell script
./mvnw package -Dquarkus.package.type=uber-jar
```

The microservice is now runnable using `java -jar target/coordinate-finder-<version>-runner.jar`.

## Creating native executable

You can create a native executable using:

```shell script
./mvnw package -Pnative
```

Or, if you do not have GraalVM installed, you can run the native executable build in a container using:

```shell script
./mvnw package -Pnative -Dquarkus.native.container-build=true
```

You can then execute the native executable with: `./target/coordinate-finder-<version>-runner`

If you want to learn more about building native executables, please consult 
[https://quarkus.io/guides/maven-tooling.html](https://quarkus.io/guides/maven-tooling.html).

## Dockerising native executable

To make a native executable platform independent, build a Docker image of a Linux native executable.

If you do not use a Linux operating system, build a native executable for Linux first.

```shell
./mvnw package -Dnative -Dquarkus.native.container-build=true
```

Then, you can build a Docker image of this Linux native executable.

```shell
docker build -f src/main/docker/Dockerfile.native -t seifertfelix/coordinate-finder .
```

The process of creating a native executable and creating a Docker image with it can be combined in a single Maven 
command.

```shell
./mvnw package -Pnative -Dquarkus.native.container-build=true -Dquarkus.container-image.build=true
```

* `-Dquarkus.native.container-build=true` instructs Maven to build an executable for a container even though the OS is 
  not Linux.
* `-Dquarkus.container-image.build=true` instructs the extension `quarkus-container-image-docker` to create a Docker 
  image.

To run the containerised microservice, the microservice still needs to contact the two containers `postgres` and 
`keycloak`. Adding the microservice container to the network `host` is the easiest workaround without changing the 
settings of the application.

```shell
docker run -p 8082:8082 --network host seifertfelix/coordinate-finder
```

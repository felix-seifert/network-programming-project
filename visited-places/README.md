# `visited-places`

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

The command produces the `visited-places-<version>-runner.jar` file in the `/target` directory. Be aware that this is 
not an _über-jar_ as the dependencies are copied into the `target/lib` directory.

If you want to build an _über-jar_, execute the following command:

```shell script
./mvnw package -Dquarkus.package.type=uber-jar
```

The microservice is now runnable using `java -jar target/visited-places-<version>-runner.jar`.

## Creating native executable

You can create a native executable using:

```shell script
./mvnw package -Pnative
```

Or, if you do not have GraalVM installed, you can run the native executable build in a container using:

```shell script
./mvnw package -Pnative -Dquarkus.native.container-build=true
```

You can then execute the native executable with: `./target/visited-places-<version>-runner`

If you want to learn more about building native executables, please consult 
[https://quarkus.io/guides/maven-tooling.html](https://quarkus.io/guides/maven-tooling.html).

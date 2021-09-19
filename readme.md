# REST based micro-services sample

- The project is created for demonstration purposes and is not intended to be used as a live application(s)
- Three Spring Boot based Bazel projects that are standalone applications:
  - Stores (MongoDB, exposing a few Starbucks shops across north america, geo-spatial functionality)
  - Customers (JPA)
  - Customers UI (Angular and Spring Boot CLI backend)
- The customers application tries to discover a search-by-location-resource and periodically verifying it's still available (see `StoreIntegration`).
- If the remote system is found the customers app includes a link to let clients follow to the remote system and thus find stores near the customer.
- Hystrix is used to monitor the availability of the remote system, so if it fails to connect 20 times in 5 seconds (by default) the circuit will open and no more attempts will be made until after a timeout.

## Building instructions
The common workspace contains the rule `test_all` that runs all tests in customers and stores microservices.
To run all tests execute from the project folder dir:
```
bazel test //:test_all
```
### Building UI
To build the customers-stores-ui microservice please execute the commands
```
cd customers-stores-ui
```
To build the classes:
```
bazel build //src/main/java/example/ui:lib
```
To build the executable jar file: 
```
bazel build //src/main/java/example/ui:app
```
To run the application:
```
bazel run //src/main/java/example/ui:app
```

### Building customers microservice
To build the rest-microservices-customers microservice please execute the commands
```
cd rest-microservices-customers
```
To build classes:
```
bazel build //src/main/java/example/customers:lib
```
To build executable jar file:
```
bazel build //src/main/java/example/customers:app
```
To run the application:
```
bazel run //src/main/java/example/customers:app
```
To build tests:
```
bazel build //src/test/java/example/customers:test
```
To run tests:
```
bazel run //src/test/java/example/customers:test

OR

bazel test //src/test/java/example/customers:test
```

### Building stores microservice
To build the rest-microservices-store microservice please execute the commands
```
cd rest-microservices-store
```
To build classes:
```
bazel build //src/main/java/example/stores:lib
```
To build executable jar file:
```
bazel build //src/main/java/example/stores:app
```
To run the application:
```
bazel run //src/main/java/example/stores:app
```
To build tests:
```
bazel build //src/test/java/example/stores:test1
```
To run tests:
```
bazel run //src/test/java/example/stores:test1

OR

bazel test //src/test/java/example/stores:test1
```



## Running Instructions
- Before try to run the services, make sure you have Rabbitmq Server and MongoDB running on localhost.
- Make sure you have [Spring Boot for Groovy installed] (http://docs.spring.io/spring-boot/docs/current-SNAPSHOT/reference/htmlsingle/#getting-started-gvm-cli-installation)
- Make sure [Spring Cloud CLI is installed] (https://github.com/spring-cloud/spring-cloud-cli)
- To run the services, just execute "mvn spring-boot:run" in each project subfolder, and "spring run app.groovy" for the UI.

## IDE Support

To use these projects in an IDE you will need the [project Lombok](http://projectlombok.org/features/index.html) agent. Full instructions can be found in the Lombok website. The
sign that you need to do this is a lot of compiler errors to do with
missing methods and fields.

## Docker and Lattice

```
$ ltc create --memory-mb 0 --timeout 5m -e RABBITMQ_HOST=10.0.1.170 -e RABBITMQ_PORT=61001 customers springcloud/customers
$ ltc create --memory-mb 0 --timeout 5m -e MONGODB_HOST=10.0.1.170 -e MONGODB_PORT=61002 -e RABBITMQ_HOST=10.0.1.170 -e RABBITMQ_PORT=61001 stores springcloud/stores
```

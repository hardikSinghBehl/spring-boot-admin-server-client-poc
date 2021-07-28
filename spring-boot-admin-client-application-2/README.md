# Spring-boot application using Spring-security as client-1
#### [REFERENCED APPLICATION](https://github.com/hardikSinghBehl/xml-parser-generator-spring-boot)

### Registering as client to the created [Server Application](https://github.com/hardikSinghBehl/spring-boot-admin-server-client-poc/tree/main/Spring-boot-admin-server-application)
* Put the below dependencies in pom.xml
```
<dependency>
  <groupId>de.codecentric</groupId>
  <artifactId>spring-boot-admin-starter-client</artifactId>
  <version>${spring-boot-client-server.version}</version>
</dependency>
```
* Modify the corresponding .properties file
```
#Register as client
spring.boot.admin.client.url=<SERVER HOST AND PORT GOES HERE>
spring.boot.admin.client.username=<SERVER USERNAME GOES HERE>
spring.boot.admin.client.password=<SERVER PASSWORD GOES HERE>
spring.boot.admin.client.auto-registration=true
spring.boot.admin.client.auto-deregistration=true

spring.boot.admin.client.instance.metadata.user.name=<SERVER USERNAME GOES HERE>
spring.boot.admin.client.instance.metadata.user.password=<SERVER PASSWORD GOES HERE>
management.endpoints.web.exposure.include=*
management.endpoint.health.show-details=always
```
* The above server url, username and password should match the values configured in server application
* After the server is up, this application would register itself with the server application automatically

### Project Setup

* Install Java 16
* Install Maven

Recommended way is to use [sdkman](https://sdkman.io/) for installing both maven and java

Run mvn clean install in the core

```
mvn clean install
```
Execute any of the two commands below to run the application

```
java -jar target/spring-boot-data-to-xml-0.0.1-SNAPSHOT.jar
```

```
mvn spring-boot:run
```
Or click run as spring boot application in your IDE
```
Run as -> Spring boot application
```
The port is configured to 9090, the swagger-ui can be viewed at the below URI
```
http://localhost:9090/swagger-ui.html
```

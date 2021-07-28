# Server application
#### Service to monitor and manage Spring boot applications under the root repository
#### This application serves as the admin-server application to which existing/new application(s) are to be registered as a client which will enable this service to monitor the registered services (The steps for registration are mentioned below)
#### [Spring-boot-admin](https://github.com/codecentric/spring-boot-admin) provides a UI to all the endpoints exposed by [Project Actuator](https://docs.spring.io/spring-boot/docs/current/reference/html/actuator.html)
---
# Setup a client
* Put the below dependencies in pom.xml
```
<dependency>
  <groupId>de.codecentric</groupId>
  <artifactId>spring-boot-admin-starter-client</artifactId>
  <version>${spring-boot-client-server.version}</version>
</dependency>
```
* Modify the corresponding .properties file with the below mentioned keys
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
* The above server url, username and password can be found in application.properties file of this server application
* If using Spring security or any authorization layer in application (i.e API(s) are private) make sure to exclude authentication and authorization from all the actuator endpoints (i.e make them public)
```
.antMatchers("/actuator/**").permitAll()
```
---
# Key details in Admin-Server application
* Email Notification for service status change has been configured, the details can be found in application.properties
* To add email(s) that are to be notified of the status change, put them against the below mentioned key seperated by a comma
```
spring.boot.admin.notify.mail.to=hardik.behl7444@gmail.com
```
* The email that will be used to notify recipients of service status change is to be configured using the below mentioned key(s) in .properties file  with it's corresponding [APP PASSWORD](https://devanswers.co/create-application-specific-password-gmail/)
```
spring.mail.username = <Email-id goes here>
spring.mail.password = <App Password here>
```
* The Username and password are configured against the below mentioned keys in .properties file (The same will be used when registering a client, Recommended not to change if not urgently required. If changed, the same has to be reflected in it's registered client applications)
```
spring.security.user.name=<Admin Server Username goes here>
spring.security.user.password=<Admin Server Password goes here>
```

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
java -jar target/spring-boot-admin-server-0.0.1-SNAPSHOT.jar
```

```
mvn spring-boot:run
```

The admin module can be viewed by visiting the below link
```
http://localhost:8093
```
Use the username and password configured in application.properties file to login


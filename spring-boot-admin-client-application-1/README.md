# Description

##### Reference POC for using JWT based Authentication and Authorization on REST endpoints with Spring Security


# Setup

```
mvn clean install
```
# Optional

* base url and port can be changed in appliication.properties
* secret-key value can be changed in application.properties
* **default port is 1405 and base url is /dory (http://localhost:1405/dory)**

# API

* POST /user to make new user account in the database **(No Authentication Needed)**
* POST /user/login returns a JWT token on login success representing the user **(No Authentication Needed)**
* GET /joke returns a joke **(Authentication Needed, needs header with name 'Authorization' with value 'Bearer <recieved JWT token>')**

# JWT 
* received JWT can be pasted on jwt.io and decrypted version can be seen

# Main Classes
* SecurityConfiguration.class
* CustomUserDetailService.class
* JwtAuthenticationFilter.class
* JwtUtils.java

# Request Flow

* Any request to an endpoint that is specified to be authenticated (SecurityConfiguration.class) will be intercepted by the JwtAuthenticationFilter.class (also specifed inside SecurityConfiguration.class addFilterBefore())
* inside JwtAuthenticationFilter (extending OncePerRequestFilter), we extract the token from request header, decrypt it and load the user in database for which the jwt was generated (user details are inside token, refer JwtUtils.class) and set authentication inside SecurityContextHolder object
* when loadUserByUsername() inside class that extends UserDetailsService returns a valid UserDetails object then the token is fully authenticated and request to the controller method goes through successfully and executes
* the authentication object inside SecurityContextHolder holds the username and password of currently authenticated user

# Technologies used 
* Spring Boot 
* Spring Security
* H2 Database (in-memory, persisted data will vanish on application stopage)
* jjwt library (https://mvnrepository.com/artifact/io.jsonwebtoken/jjwt)
* lombok

# Resources
* https://oauth.net/2/
* https://www.youtube.com/watch?v=rBNOc4ymd1E


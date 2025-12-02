# Getting Started

## Used Frameworks and Libraries
Spring Boot: 3.5.9 (SNAPSHOT)

* Developer Tools
  * GraphQL DGS Code Generation
  * Spring Boot DevTools
* Web
  * Spring Web
  * Spring for GraphQL
  * Rest Repositories
  * Spring Session for Spring Data Redis
  * Spring Session for JDBC
* Security
  * Spring Security
  * OAuth2 Authorization Server
* SQL
  * Spring Data JPA
  * Liquibase Migration
  * H2 Database
  * MySQL Driver
* Messaging
  * Spring Integration
  * Spring for Apache Kafka
* I/O
  * Java Mail Sender
  * Quartz Scheduler
* Ops
  * Spring Boot Actuator


## Reference Documentation

For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/3.5.9-SNAPSHOT/maven-plugin)
* [Create an OCI image](https://docs.spring.io/spring-boot/3.5.9-SNAPSHOT/maven-plugin/build-image.html)
* [Spring Integration JPA Module Reference Guide](https://docs.spring.io/spring-integration/reference/jpa.html)
* [Spring Integration Test Module Reference Guide](https://docs.spring.io/spring-integration/reference/testing.html)
* [Spring Integration Apache Kafka Module Reference Guide](https://docs.spring.io/spring-integration/reference/kafka.html)
* [Spring Integration Mail Module Reference Guide](https://docs.spring.io/spring-integration/reference/mail.html)
* [Spring Integration Security Module Reference Guide](https://docs.spring.io/spring-integration/reference/security.html)
* [Spring Integration HTTP Module Reference Guide](https://docs.spring.io/spring-integration/reference/http.html)
* [Spring Boot Actuator](https://docs.spring.io/spring-boot/3.5.9-SNAPSHOT/reference/actuator/index.html)
* [Spring Data JPA](https://docs.spring.io/spring-boot/3.5.9-SNAPSHOT/reference/data/sql.html#data.sql.jpa-and-spring-data)
* [Rest Repositories](https://docs.spring.io/spring-boot/3.5.9-SNAPSHOT/how-to/data-access.html#howto.data-access.exposing-spring-data-repositories-as-rest)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/3.5.9-SNAPSHOT/reference/using/devtools.html)
* [Spring for GraphQL](https://docs.spring.io/spring-boot/3.5.9-SNAPSHOT/reference/web/spring-graphql.html)
* [Spring Integration](https://docs.spring.io/spring-boot/3.5.9-SNAPSHOT/reference/messaging/spring-integration.html)
* [Spring for Apache Kafka](https://docs.spring.io/spring-boot/3.5.9-SNAPSHOT/reference/messaging/kafka.html)
* [Liquibase Migration](https://docs.spring.io/spring-boot/3.5.9-SNAPSHOT/how-to/data-initialization.html#howto.data-initialization.migration-tool.liquibase)
* [Java Mail Sender](https://docs.spring.io/spring-boot/3.5.9-SNAPSHOT/reference/io/email.html)
* [OAuth2 Authorization Server](https://docs.spring.io/spring-boot/3.5.9-SNAPSHOT/reference/web/spring-security.html#web.security.oauth2.authorization-server)
* [Quartz Scheduler](https://docs.spring.io/spring-boot/3.5.9-SNAPSHOT/reference/io/quartz.html)
* [Spring Security](https://docs.spring.io/spring-boot/3.5.9-SNAPSHOT/reference/web/spring-security.html)
* [Spring Session for Spring Data Redis](https://docs.spring.io/spring-session/reference/)
* [Spring Session for JDBC](https://docs.spring.io/spring-session/reference/)
* [Spring Web](https://docs.spring.io/spring-boot/3.5.9-SNAPSHOT/reference/web/servlet.html)

## Guides

The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service with Spring Boot Actuator](https://spring.io/guides/gs/actuator-service/)
* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
* [Accessing JPA Data with REST](https://spring.io/guides/gs/accessing-data-rest/)
* [Accessing Neo4j Data with REST](https://spring.io/guides/gs/accessing-neo4j-data-rest/)
* [Accessing MongoDB Data with REST](https://spring.io/guides/gs/accessing-mongodb-data-rest/)
* [Building a GraphQL service](https://spring.io/guides/gs/graphql-server/)
* [Integrating Data](https://spring.io/guides/gs/integration/)
* [Accessing data with MySQL](https://spring.io/guides/gs/accessing-data-mysql/)
* [Securing a Web Application](https://spring.io/guides/gs/securing-web/)
* [Spring Boot and OAuth2](https://spring.io/guides/tutorials/spring-boot-oauth2/)
* [Authenticating a User with LDAP](https://spring.io/guides/gs/authenticating-ldap/)
* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)

## GraphQL code generation with DGS

This project has been configured to use the Netflix DGS Codegen plugin.
This plugin can be used to generate client files for accessing remote GraphQL services.
The default setup assumes that the GraphQL schema file for the remote service is added to the
`src/main/resources/graphql-client/` location.

You can learn more about the [plugin configuration options](https://github.com/deweyjose/graphqlcodegen) and
[how to use the generated types](https://netflix.github.io/dgs/generating-code-from-schema/) to adapt the default setup.

## Maven Parent overrides

Due to Maven's design, elements are inherited from the parent POM to the project POM.
While most of the inheritance is fine, it also inherits unwanted elements like `<license>` and `<developers>` from the
parent.
To prevent this, the project POM contains empty overrides for these elements.
If you manually switch to a different parent and actually want the inheritance, you need to remove those overrides.

## Administration Module
The `administration` submodule packages the Keycloak-protected React UI and its Spring Boot GraphQL backend. To build it locally run:
```powershell
cd C:\Users\SCMJ178\IdeaProjects\liquidVote
mvn -pl administration clean test
```
This triggers the React build (via `npm install` / `npm run build`) and bundles the resulting files under `static/admin` inside the WAR.

### Keycloak & Environment
Set the following variables (for both backend and frontend) to point at your realm-specific gateway:
- `VITE_KEYCLOAK_URL`
- `VITE_KEYCLOAK_REALM`
- `VITE_KEYCLOAK_CLIENT_ID`
- `KEYCLOAK_ISSUER_URI`
- `KEYCLOAK_CLIENT_ID`

The admin GraphQL endpoints require the `userAdmin` role. Use the new `/admin` route to access the UI once the WAR is deployed.

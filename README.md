MicroService- Spring Cloud Config Server

INTRODUCTION
In the world of Microservice, configurations plays an important role. As the number of microservice increases, maintaining and managing the configurations related properties is a tedious part.
In microservice environment, each service will have different properties as per different profiles i.e. (properties based on environment DEV environment might have different set of value where as PROD environment will have different properties)

Hence, the need of CENTRALISED CONFIGURATION SERVICE

Spring Cloud Config Server comes to rescue which can be connected to GIT.
It is responsible for maintaining the properties and client will retrieve them. It is possible to update the configuration properties at runtime without restarting or redeploying the application.
GIT is a place where we can have multiple properties committed based on different profiles in centralised location.
We will have multiple services which will be connected to Config server and this config server be connected to GIT.

In this project, we will start with how we read different kinds of proeprties and connect it with Config Server.

The property file naming rule

Since all the property files related to different services are placed in a single location, there should be a way to distinguish them among services. Otherwise the Spring Cloud Config Server will have to face for a problem when picking up the correct configuration file for the service. This can be achieved with name of the property file. The property file should be named based on following rules.

The name should start with the application name as declared in the relevant service.
e.g:-  spring.application.name = department-service

If there different profiles, the profile name should comes after the application name.
e.g:- department-service-uat.properties , department-service-qa.properties  (or .yml)

The spring cloud config server will pick the correct property file based on the application name and the activated profile name. If no profile is activated/mentioned explicitly, it will pick the property file with no profile suffix.

#Spring Boot Annotations for reading Property

*Different Annotations used*

@Value: In this we can define the exact property key defined in application.properties/yaml file
For example: In application.properties, we have property as server.port = 8081.
We can directly used it as @Value(${server.port})

@ConfigurationProperties: This annotation is used tel Spring that this bean will be used for configuration files.

@PropertySource: This annotation is used, if our properties are not in default property file i.e. application.properties and the properties are defined in some other property file.
@PropertySource is used in combination to @ConfigurationProperties

Spring smartly detects properties based on name i.e. If we are having same propertyName as key then it is autmatically detected.

Creating Spring Cloud Config Server

1.) Go to Spring Initializer
2.) Add dependencies i.e. Config Server
3.) Create Project

How to start Config Server

Import this project and on top of main class annotate it with @EnableConfigServer.
In application.properties, we need to add git URI with key spring.cloud.config.server.git.uri = value

NOTE: If we are using Local GIT, then add file://Value.

For Example: spring.cloud.config.server.git.uri = file:///C:/Paras/configurations

In your git folder, add and commit your files in your git folder

That`s it. Your Spring Cloud Config Server is ready.


Connecting Different Service with Spring Cloud Config Server

1.) Change the property file name from application.properties to bootstrap.properties
Reason, Proeprties are read at  startup time and are required. Since bootstarp.properties are read before application.properties

2,) In boostrap.properties, add following key

spring.application.name = applicationName

NOTE: This is very important. Since in Git, this name is going to be used for saving the file.
As Spring will read properties from this file from Config Server

We need to tell Spring about Config Server URI

spring.cloud.config.uri = SpringCloudConfigServer_URI

For example:
spring.cloud.config.uri = http://localhost:8888

So, Spring will internally read properties using combination of spring.cloud.config.uri+spring.application.name

That`s it.




Problem --> Now, services will be up and running. But we have one problem, Since properties are read at runtime. Even if we have made changes in properties, that won`t be reflected.

Solution?

@RefreshScope

We need to annotate the bean with @RefreshScope and it will refresh the properties.
We can have multiple files where configurations have been configured, so instead of having it on top of different beans, we can have it on top of main class and we need not add in different places.

@RefreshScope makes use of actuator`s refresh end-point to refresh and read the updated configurations.

For this reason, we need to enable actuator`s refresh endpoint

management.endpoints.web.exposure.include=*
management.security.enabled=false

ENDPOINT`s to access the services

http://localhost:8080/properties/about --> It read the very basic property which is having simple name

http://localhost:8080/properties/details --> It reads the properties which is having combination of different objects

http://localhost:8080/properties/skills --> It is reading property from non-default property file

http://localhost:8080/properties/achievements --> It is reading property from non-default property file

http://localhost:8080/actuator/refresh

We can also refer to properties from Spring Config Server

http://localhost:8888/propertyFileNameOfRespectiveService/profile

In this example, it will be http://localhost:8888/configurations/default

# spring-cloud-microservice
- git-local-config-repo
    - stores/holds all configurations/properties for different environment 
    - create the configuration in this module and make it as local git repo and commit all the changes. or the config-repo will not load the change in properties
- spring-cloud-config-repo
    - server port - 8888
    - loads all configuration/properties from git-local-config-repo
- limits-service (exposed service)
    - on default port 8080
    - service which will connect to spring-cloud-config-repo to fetch properties for corresponding environment.
    - spring-cloud-config-repo internally connects to git-local-config-repo to load property
    - remove default values set in application.properties
    - rename application.properties to different name so there is no default value loaded from this application.properties, instead values are loaded by config-server
        - configure spring.cloud.config.uri in properties file
        - spring.application.name should match the properties file name in git-local-config-repo
    - http://localhost:8080/limits/ will show the default values from limits-service.properties
    - configure profiles (dev/sandbox/int/stage/prod)
        - for example change the properties in your service module spring.profiles.active=dev
        - can be parameterized using vm arg 
- Currency-exchange-service
    - Contains exchange values
    - server port - 8000
    - Use Environment Interface from spring evn package to access environment properties
        - for example environment.getProperty("local.server.port")
    - dynamic vm args can be passed for server.port
        - for example vm args while starting the application "-Dserver.port=8000"
    - JPA
        - include spring-starter-data, and h2db
        - convert ExchangeValue to entity
        - create data.sql file in resource and feed the default values in the h2db
        - spring.jpa.show-sql=true  
        - spring.h2.console.enabled=true 
        - access h2-console via http://localhost:8000/h2-console
        - create a repository class to send the correct response by extending JpaRepository Interface
        - Spring data jpa has lot of implementation built-in for example findby**
- Currency-conversion-service
    - Integrates currency-exchange-service using RestTemplate (refer CurrencyConversionController)
    - server port - 8100
    - Fiegn(rest service client) to invoke rest service, provides ribbon client side load balancing (Netflix OpenSource)
        - spring-cloud-starter-openfeign artifact in pom
        - enable feign in application class with annotation @EnableFeignClients("com.venkyms.microservices.currencyconversionservice")
        - create fiegn proxy to integrate external service CurrencyExchangeServiceProxy
    - Ribbon - loadbalancing
        - spring-cloud-starter-netflix-ribbon artifact in pom
        - Enable ribbon on proxy class
        - @RibbonClient(name = "currency-exchange-service") , once ribbon client is enabled url is not required in Feign. instead define ribbon.listOfServers for load balancing
         

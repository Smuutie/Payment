## Payment demo project

### Setup

To run the application in development you need to first create and start the database and keycloak containers.  
To do that run the following command:
```shell
docker compose up -d
```

After that you will have to build the application and start it via Gradle.

```shell
./gradlew clean bootRun
```

This will setup the database and import sample merchants.  
To login to the application you can use credentials for keycloak "merchant/merchant" or "admin/admin".  
This will allow you to get a token from the token endpoint.  

### Postman
If you want to make any requests feel free to use the exported postman collection.   
There are sample requests for merchants and transactions.  
<i>Don't forget to change the transaction IDs.</i>  

Get endpoints will always return page of the objects.

### Keycloak
Currently keycloak has an exported realm in a json file that is located in the config.  
From there we are importing a sample realm and a client to which we are authenticating and validating the token against.  

### Lint and formatter
To format use 
```shell
./gradlew spotlessApply
```
This will format the code with the Google style.  

Code is linted by checkstyle.  
You can find the configurations in ./config/checkstyle/  
Every run of the check gradle task will invoke the checkstyle and it will not pass unless code is compliant.  


### Design decisions

I chose to use separate services implementations for different types of transactions to allow for better code separation and clarity.  
Factory will check the type of transaction and will invoke the correct service.  
In traditional factory pattern we could have just returned the interface that describes the transaction service.
It would have been more abstract but would have made the testing a bit harder as we would have had to mock the interface instead.  

Additionally, this is allowing us to extend in the future the number of controllers to provide specific controller for each type of transaction.  
In case we decide to split the transactions in different tables it would be a little faster when displaying transaction of just one type.  
However this would have been at the cost of read speed in the case we are displaying all transaction types.  

As it is considered a good practise to split the entity and DTO objects I chose to use model mapper.  
This library only advantage is explicit mappings and could be replaced by MapStruct which is even a little faster solution as it does not use reflections.

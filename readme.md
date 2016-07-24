This service uses the dropwizard framework. See [here] (http://www.dropwizard.io/0.9.3/docs/getting-started.html) as to how to get started with dropwizard.


## to build jar...
```
./gradlew shadowjar
```

## to run the service...

```
./gradlew run
```

or to run the fat jar manually...

```
java -jar build/libs/socialeggbox-ws.jar  server config/socialeggbox.yml
```

see [here] (https://karollotkowski.wordpress.com/2015/10/13/run-dropwizard-with-gradle) for gradle + dropwizard integration 

## to run the cucumber tests...
```
./gradlew cucumber
```

#To look at later...
##java.time LocalDateTime binding to MySQL Timestamp
Was getting this error 
```
org.skife.jdbi.v2.exceptions.UnableToExecuteStatementException: 
org.h2.jdbc.JdbcSQLException: Cannot parse "TIMESTAMP" constant 
```

This [link] (http://clearthehaze.com/2016/06/jdbi-binders-java-8-times/) explains how to fix error, but I'm not happy with the solution - see DBAvailability.save method - I have to individually bind the start and end date, rather than going through the @BindBean. The moethod signature looks clumsy

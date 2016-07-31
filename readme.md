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

##Solved Issues
###Error No 1 - java.time LocalDateTime binding to MySQL Timestamp
If you get this error
```
org.skife.jdbi.v2.exceptions.UnableToExecuteStatementException: 
org.h2.jdbc.JdbcSQLException: Cannot parse "TIMESTAMP" constant 
```

This [link] (http://stackoverflow.com/questions/30677731/how-do-i-modify-my-sqlupdate-to-convert-a-joda-datetime-object-into-an-h) explains how to do it
Bascially...
We can do this by registering argument factory to DBI.
```
public class DateTimeArgumentFactory implements ArgumentFactory<DateTime> {
    @Override
    public boolean accepts(Class<?> expectedType, Object value, StatementContext ctx) {
        return value != null && DateTime.class.isAssignableFrom(value.getClass());
    }

    @Override
    public Argument build(Class<?> expectedType, final DateTime value, StatementContext ctx) {
        return new Argument() {
            @Override
            public void apply(int position, PreparedStatement statement, StatementContext ctx) throws SQLException {
                statement.setTimestamp(position, new java.sql.Timestamp(value.getMillis()));
            }
        };
    }
}
```
Register this argument factory to DBI by,

```
  dbi.registerArgumentFactory(new DateTimeArgumentFactory());
```
This will automatically take care of converting DateTime to sql TimeStamp.

###Error No 2
trying to deserialize payload to object called Thing with 2 fields - field1 & field 2

org.codehaus.jackson.map.JsonMappingException: No suitable constructor found for type [simple type, class Thing]: can not instantiate from JSON object (need to add/enable type information?)

need no arg constructor for class Thing

```
org.codehaus.jackson.map.exc.UnrecognizedPropertyException: Unrecognized field "field1" (Class Thing), not marked as ignorable
```
 
need setter for every field you wish to deserialise
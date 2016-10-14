[![Dependency Status](https://www.versioneye.com/user/projects/5800a283a23d52004d0e29e9/badge.svg)](https://www.versioneye.com/user/projects/5800a283a23d52004d0e29e9)

[![Coverage Status](https://coveralls.io/repos/github/derekclark/socialcalendar-webservice/badge.svg?branch=master)](https://coveralls.io/github/derekclark/socialcalendar-webservice?branch=master)

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

###Error No 3
trying to deserialize payload to object for joda-time
com.fasterxml.jackson.databind.JsonMappingException: Can not instantiate value of type [simple type, class org.joda.time.DateTime] from String value ('2014-08-20T11:51:31.233Z'); no single-String constructor/factory method
 at [Source: { 
  "timestamp" : "2014-08-20T11:51:31.233Z" 

  You need to tell the jackson ObjectMapper how to handle JodaTime object. Do this by registering the JodaTime class with the ObjectMapper...

  ```
  mapper.registerModule(new JodaModule());
  ```

  Here is a full example
  ```
  import java.io.IOException;

import org.joda.time.DateTime;
import org.junit.Test;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;

public class JacksonTest {

    private static final String json = "{ \n" +
            "  \"timestamp\" : \"2014-08-20T11:51:31.233Z\" \n" +
            "}";

    @Test
    public void test() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JodaModule());

        System.out.println(mapper.readValue(json, GreetingResource.class));
    }
}

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
class GreetingResource {
    @JsonProperty("timestamp")
    private DateTime date;

    public DateTime getDate() {
        return date;
    }

    public void setDate(DateTime date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "GreetingResource{" +
                "date=" + date +
                '}';
    }
}
```

This will deserialize datetime into this format
```
2014-08-20T11:51:31.233Z
```

You could write your own serializer and deserializer if you wanted to alter the datetime format.
```
    @JsonSerialize(using = CustomDateSerializer.class)
    public DateTime getEndDateTime() {
        return endDateTime;
    }
    @JsonDeserialize(using = CustomDateDeserializer.class)
    public void setEndDateTime(DateTime endDateTime) {
        this.endDateTime = endDateTime;
    }
```

```
package utilities;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.io.IOException;

public class CustomDateSerializer extends JsonSerializer<DateTime> {

    private static DateTimeFormatter formatter =
            DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss");

    @Override
    public void serialize(DateTime value, JsonGenerator gen,
                          SerializerProvider arg2)
            throws IOException, JsonProcessingException {

        gen.writeString(formatter.print(value));
    }
}
```

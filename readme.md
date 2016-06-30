## to build jar...
```
./gradlew shadowjar
```

## to run the service...

```
./gradlew run
```

or to run the fatjar manually...

```
java -jar build/libs/socialeggbox-ws.jar  server config/socialeggbox.yml
```

see [here] (https://karollotkowski.wordpress.com/2015/10/13/run-dropwizard-with-gradle) for gradle + dropwizard integration 

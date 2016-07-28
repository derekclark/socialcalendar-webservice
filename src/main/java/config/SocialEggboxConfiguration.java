package config;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Environment;
import representation.AvailabilityDAO;
import representation.SocialEggboxEndpointV1;
import representation.UserDAO;

public class SocialEggboxConfiguration extends Configuration{
    @JsonProperty("database")
    private DatabaseConfiguration databaseConfiguration;

    public void setDatabaseConfiguration(DatabaseConfiguration databaseConfiguration) {
        this.databaseConfiguration = databaseConfiguration;
    }

    public SocialEggboxEndpointV1 createEndpointV1(Environment environment) {
        return new SocialEggboxEndpointV1(createUserRepository(environment), createAvailabilityRepository(environment));
    }

    private AvailabilityDAO createAvailabilityRepository(Environment environment) {
        return databaseConfiguration.createAvailabilityRepository(environment);
    }

    public UserDAO createUserRepository(Environment environment) {
        return databaseConfiguration.createUserRepository(environment);
    }
}

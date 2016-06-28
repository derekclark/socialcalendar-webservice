package config;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Environment;
import representation.SocialEggboxEndpointV1;
import representation.UserDAO;

public class SocialEggboxConfiguration extends Configuration{
    @JsonProperty("database")
    private DatabaseConfiguration databaseConfiguration;

    public SocialEggboxEndpointV1 createEndpointV1(Environment environment) {
        return new SocialEggboxEndpointV1(createRepository(environment));
    }

    public UserDAO createRepository(Environment environment) {
        return databaseConfiguration.createRepository(environment);
    }
}

package config;

import io.dropwizard.Configuration;
import io.dropwizard.setup.Environment;
import representation.SocialEggboxEndpointV1;
import representation.UserDAO;

public class SocialEggboxConfiguration extends Configuration{
    DatabaseConfiguration databaseConfiguration = new DatabaseConfiguration();

    public SocialEggboxEndpointV1 createEndpointV1(Environment environment) {
        return new SocialEggboxEndpointV1(createRepository(environment));
    }

    public UserDAO createRepository(Environment environment) {
        return databaseConfiguration.createRepository(environment);
    }
}

package config;

import io.dropwizard.Configuration;
import io.dropwizard.setup.Environment;
import representation.SocialEggboxRepresentationV1;
import representation.UserDAO;

public class SocialEggboxConfiguration extends Configuration{

    public SocialEggboxRepresentationV1 createEndpointV1(Environment environment) {
        return new SocialEggboxRepresentationV1(new UserDAO());
    }
}

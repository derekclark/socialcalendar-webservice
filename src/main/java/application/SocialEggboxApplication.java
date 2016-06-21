package application;

import config.SocialEggboxConfiguration;
import io.dropwizard.setup.Environment;

public class SocialEggboxApplication {
    public void run(SocialEggboxConfiguration config, Environment environment) {
        environment.jersey().register(config.createEndpointV1(environment));
    }
}

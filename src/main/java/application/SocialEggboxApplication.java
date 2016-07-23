package application;

import config.SocialEggboxConfiguration;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;

public class SocialEggboxApplication  extends Application<SocialEggboxConfiguration> {
    public void run(SocialEggboxConfiguration config, Environment environment) {
        environment.jersey().register(config.createEndpointV1(environment));
    }

    public static void main(String[] args) throws Exception{
        new SocialEggboxApplication().run(args);
    }
}

package application;

import com.codahale.metrics.MetricRegistry;
import com.fasterxml.jackson.databind.ObjectMapper;
import config.DatabaseConfiguration;
import config.SocialEggboxConfiguration;
import io.dropwizard.setup.Environment;
import org.junit.Before;
import org.junit.Test;
import endpoint.SocialEggboxEndpointV1;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class SocialEggboxApplicationTest {
    private final Environment environment = new Environment("test", new ObjectMapper(), null, new MetricRegistry(), null);
    SocialEggboxConfiguration config;
    SocialEggboxApplication application;

    @Before
    public void setup(){
        application = new SocialEggboxApplication();
        config = new SocialEggboxConfiguration();
        config.setDatabaseConfiguration(new DatabaseConfiguration());
    }

    @Test
    public void EndpointV1Available(){
        application.run(config, environment);
        assertThat(registeredSingletons(environment)).hasAtLeastOneElementOfType(SocialEggboxEndpointV1.class);
    }

    private Set<Object> registeredSingletons(Environment env) {
        return env.jersey().getResourceConfig().getSingletons();
    }



}

package application;

import com.codahale.metrics.MetricRegistry;
import com.fasterxml.jackson.databind.ObjectMapper;
import config.SocialEggboxConfiguration;
import io.dropwizard.setup.Environment;
import org.junit.Test;
import representation.SocialEggboxRepresentationV1;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class SocialEggboxApplicationTest {
    private final Environment environment = new Environment("test", new ObjectMapper(), null, new MetricRegistry(), null);

    @Test
    public void EndpointV1Available(){
        SocialEggboxApplication application = new SocialEggboxApplication();
        SocialEggboxConfiguration config = new SocialEggboxConfiguration();
        application.run(config, environment);
        assertThat(registeredSingletons(environment)).hasAtLeastOneElementOfType(SocialEggboxRepresentationV1.class);
    }

    private Set<Object> registeredSingletons(Environment env) {
        return env.jersey().getResourceConfig().getSingletons();
    }



}

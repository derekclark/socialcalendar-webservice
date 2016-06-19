package config;

import com.codahale.metrics.MetricRegistry;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.dropwizard.setup.Environment;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class SocialEggboxConfigurationTest {
    SocialEggboxConfiguration config = new SocialEggboxConfiguration();

    @Test
    public void canCreateV1Endpoint(){
        Environment environment = new Environment("test", new ObjectMapper(), null, new MetricRegistry(), null);
        assertNotNull(config.createEndpointV1(environment));
    }

}

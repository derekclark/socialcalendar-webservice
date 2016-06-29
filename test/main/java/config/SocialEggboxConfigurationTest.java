package config;

import com.codahale.metrics.MetricRegistry;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.dropwizard.setup.Environment;
import org.junit.Before;
import org.junit.Test;
import representation.UserDAO;

import static org.junit.Assert.assertNotNull;

public class SocialEggboxConfigurationTest {
    SocialEggboxConfiguration config = new SocialEggboxConfiguration();

    @Before
    public void setup(){
        config.setDatabaseConfiguration(new DatabaseConfiguration());
    }

    @Test
    public void canCreateV1Endpoint(){
        Environment environment = new Environment("test", new ObjectMapper(), null, new MetricRegistry(), null);
        assertNotNull(config.createEndpointV1(environment));
    }

    @Test
    public void createsRepository(){
        Environment environment = new Environment("test", new ObjectMapper(), null, new MetricRegistry(), null);
        assert(config.createRepository(environment) instanceof UserDAO);
    }
}

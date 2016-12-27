package application;

import config.SocialEggboxConfiguration;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;
import org.eclipse.jetty.servlets.CrossOriginFilter;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import java.util.EnumSet;

public class SocialEggboxApplication  extends Application<SocialEggboxConfiguration> {
    public void run(SocialEggboxConfiguration config, Environment environment) {
        environment.jersey().register(config.createEndpointV1(environment));
        enableCORSHeaders(environment);
    }

    private void enableCORSHeaders(Environment environment) {
        final FilterRegistration.Dynamic cors =
                environment.servlets().addFilter("CORS", CrossOriginFilter.class);

        cors.setInitParameter("allowedOrigins", "*");
        cors.setInitParameter("allowedHeaders", "X-Requested-With,Content-Type,Accept,Origin");
        cors.setInitParameter("allowedMethods", "OPTIONS,GET,PUT,POST,DELETE,HEAD");
        
        cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
    }

    public static void main(String[] args) throws Exception{
        new SocialEggboxApplication().run(args);
    }
}

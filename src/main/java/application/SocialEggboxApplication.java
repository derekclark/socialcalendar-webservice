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
        allowAccessForAll(environment);
    }

    private void allowAccessForAll(Environment environment) {
        FilterRegistration.Dynamic filter = environment.servlets().addFilter(
                "CORS", CrossOriginFilter.class);
        filter.setInitParameter(CrossOriginFilter.ALLOWED_METHODS_PARAM,
                "GET,POST,PUT,DELETE");
        filter.setInitParameter(CrossOriginFilter.ALLOWED_ORIGINS_PARAM, "*");
        filter.setInitParameter(
                CrossOriginFilter.ACCESS_CONTROL_ALLOW_ORIGIN_HEADER, "*");
        filter.setInitParameter("allowedHeaders",
                "Content-Type,Authorization,X-Requested-With,Content-Length,Accept,Origin");
        filter.setInitParameter("allowCredentials", "true");

        filter.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
    }

    public static void main(String[] args) throws Exception{
        new SocialEggboxApplication().run(args);
    }
}

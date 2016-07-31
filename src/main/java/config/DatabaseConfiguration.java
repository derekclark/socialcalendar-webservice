package config;

import com.fasterxml.jackson.annotation.JsonProperty;
import database.DBAvailability;
import database.DBUser;
import database.DateTimeArgumentFactory;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.setup.Environment;
import org.flywaydb.core.Flyway;
import org.h2.jdbcx.JdbcDataSource;
import org.skife.jdbi.v2.DBI;
import representation.AvailabilityDAO;
import representation.UserDAO;

import javax.sql.DataSource;

public class DatabaseConfiguration extends DataSourceFactory{
    @JsonProperty("in_memory")
    private boolean inMemory = true;

    public UserDAO createUserRepository(Environment environment) {
        DBI dbi = getDBI(environment);
        return new UserDAO(dbi.onDemand(DBUser.class));
    }

    public AvailabilityDAO createAvailabilityRepository(Environment environment) {
        DBI dbi = getDBI(environment);
        dbi.registerArgumentFactory(new DateTimeArgumentFactory());
        return new AvailabilityDAO(dbi.onDemand(DBAvailability.class));
    }

    private DBI getDBI(Environment environment) {
        DataSource dataSource = getDatasource(environment);
        migrate(dataSource);
        return new DBI(dataSource);
    }

    private void migrate(DataSource dataSource) {
        Flyway flyway = new Flyway();
        flyway.setDataSource(dataSource);
        flyway.setLocations("db/migration");
        flyway.migrate();
    }

    private DataSource getDatasource(Environment environment) {
        if (inMemory) {
            System.out.println("in memory database selected");
            return getInMemory();
        }else{
            System.out.println("real database selected");
            return build(environment.metrics(), "database");
        }
    }

    private DataSource getInMemory() {
        JdbcDataSource dataSource = new JdbcDataSource();
        dataSource.setUrl("jdbc:h2:mem:branch;DB_CLOSE_DELAY=-1");
        return dataSource;
    }
}

package config;

import database.DBUser;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.setup.Environment;
import org.flywaydb.core.Flyway;
import org.h2.jdbcx.JdbcDataSource;
import org.skife.jdbi.v2.DBI;
import representation.UserDAO;

import javax.sql.DataSource;

public class DatabaseConfiguration extends DataSourceFactory{
    public UserDAO createRepository(Environment environment) {
        DBI dbi = getDBI(environment);
        return new UserDAO(dbi.onDemand(DBUser.class));
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
        return getInMemory();
    }

    private DataSource getInMemory() {
        JdbcDataSource dataSource = new JdbcDataSource();
        dataSource.setUrl("jdbc:h2:mem:branch;DB_CLOSE_DELAY=-1");
        return dataSource;
    }
}

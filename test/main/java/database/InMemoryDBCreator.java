package database;

import org.flywaydb.core.Flyway;
import org.h2.jdbcx.JdbcDataSource;
import org.skife.jdbi.v2.DBI;

import javax.sql.DataSource;

public class InMemoryDBCreator {
    private final DBI dbi;
    private final Flyway flyway = new Flyway();

    public InMemoryDBCreator() {
        JdbcDataSource dataSource = createDataSource();
        migrate(dataSource);
        dbi = new DBI(dataSource);
        dbi.registerArgumentFactory(new DateTimeArgumentFactory());
    }

    public <T> T create(Class<? extends T> clazz) {
        return dbi.onDemand(clazz);
    }

    public void clean() {
        flyway.clean();
    }

    private JdbcDataSource createDataSource() {
        JdbcDataSource dataSource = new JdbcDataSource();
        dataSource.setUrl("jdbc:h2:mem:db_creator_future_update;DB_CLOSE_DELAY=-1");
        dataSource.setUser("");
        dataSource.setPassword("");
        return dataSource;
    }

    private void migrate(DataSource dataSource) {
        flyway.setDataSource(dataSource);
        flyway.setLocations("db/migration");
        flyway.migrate();
    }
}

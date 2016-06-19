package config;

import database.DBUser;
import database.Repository;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.setup.Environment;
import org.flywaydb.core.Flyway;
import org.h2.jdbcx.JdbcDataSource;
import org.skife.jdbi.v2.DBI;

import javax.sql.DataSource;

public class DatabaseConfiguration extends DataSourceFactory{
    public Repository createBranchRepository(Environment environment) {
        DBI dbi = getDBI(environment);
        return new Repository(dbi.onDemand(DBUser.class));
    }

    private DBI getDBI(Environment environment) {
        DataSource dataSource = getDatasource(environment);
        migrate(dataSource);
        createInMemoryData(dataSource);
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

    private void createInMemoryData(DataSource dataSource) {
        DBI dbi = new DBI(dataSource);
        org.skife.jdbi.v2.Handle h = dbi.open();

        h.execute("insert into USERHIBERNATEMODEL  (email, name, facebook_Id) values (?, ?, ?)", "derekclark14@googlemail.com", "Derek Clark", "inmem");
        h.execute("insert into USERHIBERNATEMODEL  (email, name, facebook_Id) values (?, ?, ?)", "amiclark1976@gmail.com", "Alfie Clark", "100007212617286");
        h.execute("insert into USERHIBERNATEMODEL  (email, name, facebook_Id) values (?, ?, ?)", "amiclark1974@gmail.com", "Billy Clark", "100004697869160");
        h.execute("insert into USERHIBERNATEMODEL  (email, name, facebook_Id) values (?, ?, ?)", "amiclark1975@gmail.com", "Ami Clark", "100007212617286");

    }


}

package config;

import database.Repository;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.setup.Environment;

public class DatabaseConfiguration extends DataSourceFactory{
    public Repository createBranchRepository(Environment environment) {
//        DBI dbi = getDBI(environment);
//        return new DBUserRepository(dbi.onDemand(UserDB.class));
        return new Repository();
    }


}

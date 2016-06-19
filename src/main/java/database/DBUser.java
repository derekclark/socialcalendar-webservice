package database;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;
import representation.User;

@RegisterMapper(UserMapper.class)

public interface DBUser {
    @SqlQuery("SELECT COUNT(email) FROM UserHibernateModel")
    int count();

    @SqlQuery("SELECT * FROM UserHibernateModel WHERE email = :email")
    User find(@Bind("email") String email);
}


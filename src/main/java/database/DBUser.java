package database;

import org.skife.jdbi.v2.sqlobject.*;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;
import representation.User;

@RegisterMapper(UserMapper.class)

public interface DBUser {
    @SqlQuery("SELECT * FROM UserHibernateModel WHERE email = :email")
    User findById(@Bind("email") String email);

    @SqlUpdate("INSERT INTO UserHibernateModel (email, name, facebook_Id) VALUES (:user.email, :user.name, :user.facebookId)")
    int create(@BindBean("user") User user);

    @SqlUpdate("DELETE FROM UserHibernateModel WHERE `email`= :email")
    int delete(@Bind("email") String email);


}


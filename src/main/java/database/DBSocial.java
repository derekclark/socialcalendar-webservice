package database;

import org.skife.jdbi.v2.sqlobject.*;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;
import representation.User;

@RegisterMapper(UserMapper.class)

public interface DBSocial {
    @SqlQuery("SELECT * FROM UserHibernateModel WHERE email = :email")
    User findUserById(@Bind("email") String email);

    @SqlUpdate("INSERT INTO UserHibernateModel (email, name, facebook_Id) VALUES (:user.email, :user.name, :user.facebookId)")
    int createUser(@BindBean("user") User user);

    @SqlUpdate("DELETE FROM UserHibernateModel WHERE `email`= :email")
    int deleteUser(@Bind("email") String email);


}


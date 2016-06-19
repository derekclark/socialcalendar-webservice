package database;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;
import representation.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements ResultSetMapper<User>{
    @Override
    public User map(int index, ResultSet r, StatementContext ctx) throws SQLException {
        String email = r.getString("email");
        String name = r.getString("name");
        String facebookId = r.getString("facebook_Id");
        return new User(email, name, facebookId);
    }

}

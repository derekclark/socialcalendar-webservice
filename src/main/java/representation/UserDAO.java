package representation;

import database.DBUser;

public class UserDAO {
    private DBUser dbUser;

    public UserDAO(DBUser dbUser) {
        this.dbUser = dbUser;
    }

    public int save(User user) {
        return dbUser.save(user);
    }

    public User read(String id) {
        return dbUser.findById(id);
    }
}

package representation;

import database.DBUser;

public class UserDAO {
    private DBUser dbUser;

    public UserDAO(DBUser dbUser) {
        this.dbUser = dbUser;
    }

    public int save(User user) {
        return dbUser.create(user);
    }

    public User read(String id) {
        return dbUser.findById(id);
    }

    public boolean delete(String email) {
        User user = read(email);
        if (user != null){
            dbUser.delete(email);
            return true;
        }else
            return false;
    }

}

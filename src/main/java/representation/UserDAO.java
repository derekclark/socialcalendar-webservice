package representation;

public class UserDAO {
    DBUserInMemory dbUserDatabase = new DBUserInMemory();

    public boolean save(User user) {
        dbUserDatabase.save(user);
        return true;
    }

    public User read(String id) {
        return dbUserDatabase.read(id);
    }
}

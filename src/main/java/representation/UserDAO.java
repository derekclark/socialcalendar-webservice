package representation;

import database.DBSocial;

public class UserDAO {
    private DBSocial dbSocial;

    public UserDAO(DBSocial dbSocial) {
        this.dbSocial = dbSocial;
    }

    public int save(User user) {
        return dbSocial.save(user);
    }

    public User read(String id) {
        return dbSocial.findById(id);
    }

    public boolean delete(String email) {
        User user = read(email);
        if (user != null){
            dbSocial.delete(email);
            return true;
        }else
            return false;
    }

}

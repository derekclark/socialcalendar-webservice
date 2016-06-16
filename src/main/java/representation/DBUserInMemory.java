package representation;

import java.util.HashMap;
import java.util.Map;

public class DBUserInMemory {
    Map<String, User> userDatabase = new HashMap<String, User>();

    public void save(User user){
        userDatabase.put(user.getEmail(), user);
    }

    public User read(String id) {
        return userDatabase.get(id);
    }
}

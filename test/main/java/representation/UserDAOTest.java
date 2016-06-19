package representation;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UserDAOTest {
    @Test
    public void canSaveUser(){
        User user = new User("email","name","facebookId");
        UserDAO repo = new UserDAO();
        assertTrue(repo.save(user));
    }

    @Test
    public void canReadUser(){
        User user = new User("email","name","facebookId");
        UserDAO repo = new UserDAO();
        repo.save(user);
        User returnedUser = repo.read("email");
        assertEquals(user, returnedUser);
    }


}

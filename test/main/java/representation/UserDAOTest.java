package representation;

import database.DBUser;
import database.InMemoryDBCreator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class UserDAOTest {
    DBUser repo;
    User user;
    UserDAO userDAO;

    @Before
    public void setup(){
        repo = new InMemoryDBCreator().create(DBUser.class);
        user = new User("email","name","facebookId");
        userDAO = new UserDAO(repo);
    }

    @After
    public void cleanup(){
        new InMemoryDBCreator().clean();
    }

    @Test
    public void canSaveUser(){
        assertNotNull(userDAO.save(user));
    }

    @Test
    public void canReadUser(){
        repo.save(user);
        User returnedUser = userDAO.read(user.getEmail());
        assertEquals(user, returnedUser);
    }
}

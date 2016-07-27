package representation;

import database.DBSocial;
import database.InMemoryDBCreator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class UserDAOTest {
    DBSocial repo;
    User user;
    UserDAO userDAO;

    @Before
    public void setup(){
        repo = new InMemoryDBCreator().create(DBSocial.class);
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
        repo.createUser(user);
        User returnedUser = userDAO.read(user.getEmail());
        assertEquals(user, returnedUser);
    }

    @Test
    public void canDeleteUser(){
        repo.createUser(user);
        boolean result = userDAO.delete(user.getEmail());
        assertTrue(result);

        User returnedUser = userDAO.read(user.getEmail());
        assertEquals(null, returnedUser);

    }
}

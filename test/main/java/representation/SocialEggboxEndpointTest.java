package representation;

import database.DBUser;
import database.InMemoryDBCreator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class SocialEggboxEndpointTest {
    SocialEggboxEndpointV1 representation;
    DBUser repo;

    @Before
    public void setup(){
        repo = new InMemoryDBCreator().create(DBUser.class);
        UserDAO userDAO = new UserDAO(repo);
        User user = new User("email","name","facebookId");
        repo.save(user);

        representation = new SocialEggboxEndpointV1(userDAO);
    }

    @After
    public void teardown(){
        new InMemoryDBCreator().clean();
    }

    @Test
    public void getUserByIdShouldReturn200StatusIfExists(){
        Response response = representation.getUserById("email");
        assertEquals(200, response.getStatus());
    }

    @Test
    public void getUserByIdShouldReturnUserInBodyIfExists(){
        Response response = representation.getUserById("email");
        User user = new User("email","name","facebookId");
        assertEquals(user, response.getEntity());
    }

    @Test
    public void getUserByIdShouldReturn404IfNotFound(){
        Response response = representation.getUserById("unknownUserId");
        assertEquals(404, response.getStatus());
    }

    @Test
    public void getUserByIdShouldReturnEmptyBodyIfNotFound(){
        Response response = representation.getUserById("unknownUserId");
        assertNull(response.getEntity());
    }

}

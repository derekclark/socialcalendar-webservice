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
    public static final int HTTP_STATUS_OK = 200;
    public static final int HTTP_STATUS_NOT_FOUND = 404;
    public static final String EMAIL = "email";
    public static final String USER_NAME = "name";
    public static final String FACEBOOK_ID = "facebookId";
    public static final String UNKNOWN_USER_ID = "unknownUserId";
    SocialEggboxEndpointV1 representation;
    DBUser repo;
    User savedUser = new User(EMAIL, USER_NAME, FACEBOOK_ID);

    @Before
    public void setup(){
        repo = new InMemoryDBCreator().create(DBUser.class);
        UserDAO userDAO = getUserDAO();

        representation = new SocialEggboxEndpointV1(userDAO);
    }

    private UserDAO getUserDAO() {
        UserDAO userDAO = new UserDAO(repo);
        repo.save(savedUser);
        return userDAO;
    }

    @After
    public void teardown(){
        new InMemoryDBCreator().clean();
    }

    @Test
    public void getUserByIdShouldReturn200StatusIfExists(){
        Response response = representation.getUserById(EMAIL);
        assertEquals(HTTP_STATUS_OK, response.getStatus());
    }

    @Test
    public void getUserByIdShouldReturnUserInBodyIfExists(){
        Response response = representation.getUserById(EMAIL);
        assertEquals(savedUser, response.getEntity());
    }

    @Test
    public void getUserByIdShouldReturn404IfNotFound(){
        Response response = representation.getUserById(UNKNOWN_USER_ID);
        assertEquals(HTTP_STATUS_NOT_FOUND, response.getStatus());
    }

    @Test
    public void getUserByIdShouldReturnEmptyBodyIfNotFound(){
        Response response = representation.getUserById(UNKNOWN_USER_ID);
        assertNull(response.getEntity());
    }

}

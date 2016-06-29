package representation;

import database.DBUser;
import database.InMemoryDBCreator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import utilities.JsonUtility;

import javax.ws.rs.core.Response;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class SocialEggboxEndpointTest {
    public static final int HTTP_STATUS_OK = 200;
    public static final int HTTP_STATUS_NOT_FOUND = 404;
    public static final int HTTP_STATUS_BAD_REQUEST = 400;
    public static final String EMAIL = "email";
    public static final String NAME = "name";
    public static final String FACEBOOK_ID = "facebookId";

    public static final String JSON_STRING= "{\n" +
            "  \"email\" : \"" + EMAIL + "\",\n" +
            "  \"name\" : \""+NAME+"\",\n" +
            "  \"facebookId\" : \""+FACEBOOK_ID+"\"\n" +
            "}";


    public static final String UNKNOWN_USER_ID = "unknownUserId";
    SocialEggboxEndpointV1 endpointV1;
    DBUser repo;
    User savedUser = new User(EMAIL, NAME, FACEBOOK_ID);
    User user = new User(EMAIL, NAME, FACEBOOK_ID);

    @Before
    public void setup(){
        repo = new InMemoryDBCreator().create(DBUser.class);
        UserDAO userDAO = getUserDAO();

        endpointV1 = new SocialEggboxEndpointV1(userDAO);
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
        Response response = endpointV1.getUserById(EMAIL);
        assertEquals(HTTP_STATUS_OK, response.getStatus());
    }

    @Test
    public void getUserByIdShouldReturnUserInBodyIfExists(){
        String expectedJson = JSON_STRING;
        Response response = endpointV1.getUserById(EMAIL);
        assertEquals(expectedJson, response.getEntity());
    }

    @Test
    public void getUserByIdShouldReturn404IfNotFound(){
        Response response = endpointV1.getUserById(UNKNOWN_USER_ID);
        assertEquals(HTTP_STATUS_NOT_FOUND, response.getStatus());
    }

    @Test
    public void getUserByIdShouldReturnEmptyBodyIfNotFound(){
        Response response = endpointV1.getUserById(UNKNOWN_USER_ID);
        assertNull(response.getEntity());
    }

    @Test
    public void saveUserShouldReturn200Status() throws IOException {
        String userPayload = new JsonUtility().toJson(user);
        Response response = endpointV1.saveUser(userPayload);
        assertEquals(HTTP_STATUS_OK, response.getStatus());
    }

    @Test
    public void saveUserShouldReturn400IfNoPayload(){
        String userPayload = "";
        Response response = endpointV1.saveUser(userPayload);
        assertEquals(HTTP_STATUS_BAD_REQUEST, response.getStatus());
    }

}

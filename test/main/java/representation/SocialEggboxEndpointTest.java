package representation;

import database.DBAvailability;
import database.DBSocial;
import database.InMemoryDBCreator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import utilities.JsonUtility;

import javax.ws.rs.core.Response;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Set;

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

    public static final LocalDateTime START_DATE = LocalDateTime.of(2015, 1, 2, 0, 0, 0);
    public static final LocalDateTime END_DATE = LocalDateTime.now();
    public static final String STATUS = "status";
    public static final String TITLE = "title";
    Set<User> sharedList;

    public static final String UNKNOWN_USER_ID = "unknownUserId";
    SocialEggboxEndpointV1 endpointV1;
    DBSocial userRepo;
    DBAvailability availabilityRepo;

    User savedUser = new User(EMAIL, NAME, FACEBOOK_ID);
    User user = new User(EMAIL, NAME, FACEBOOK_ID);

    @Before
    public void setup(){
        userRepo = new InMemoryDBCreator().create(DBSocial.class);
        availabilityRepo = new InMemoryDBCreator().create(DBAvailability.class);

        endpointV1 = new SocialEggboxEndpointV1(getUserDAO());
        endpointV1.setAvailabilityRepository(getAvailabilityDAO());
    }

    private void saveUser(User user){
        userRepo.createUser(user);
    }

    private UserDAO getUserDAO() {
        return new UserDAO(userRepo);
    }

    private AvailabilityDAO getAvailabilityDAO(){
        return new AvailabilityDAO(availabilityRepo);

    }

    @After
    public void teardown(){
        new InMemoryDBCreator().clean();
    }

    @Test
    public void getUserByIdShouldReturn200StatusIfExists(){
        saveUser(savedUser);
        Response response = endpointV1.getUserById(EMAIL);
        assertEquals(HTTP_STATUS_OK, response.getStatus());
    }

    @Test
    public void getUserByIdShouldReturnUserInBodyIfExists(){
        saveUser(savedUser);
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
    public void saveUserShouldReturn400IfNoPayload() throws IOException {
        String userPayload = "";
        Response response = endpointV1.saveUser(userPayload);
        assertEquals(HTTP_STATUS_BAD_REQUEST, response.getStatus());
    }

    @Test
    public void shouldReturnSavedUserPayload() throws IOException {
        String userPayload = new JsonUtility().toJson(user);
        Response response = endpointV1.saveUser(userPayload);
        assertEquals(userPayload, response.getEntity());
    }

    @Test
    public void checkUserIsSaved() throws IOException {
        String userPayload = new JsonUtility().toJson(user);
        Response response = endpointV1.saveUser(userPayload);

        response = endpointV1.getUserById(EMAIL);
        assertEquals(HTTP_STATUS_OK, response.getStatus());
        assertEquals(userPayload, response.getEntity());

    }

    @Test
    public void deleteExistingUserShouldReturn200Status(){
        saveUser(savedUser);
        Response response = endpointV1.delete(EMAIL);
        assertEquals(HTTP_STATUS_OK, response.getStatus());
    }

    @Test
    public void deleteNonExistingUserShouldReturn404Status(){
        Response response = endpointV1.delete(EMAIL);
        assertEquals(HTTP_STATUS_NOT_FOUND, response.getStatus());
    }

    @Test
    public void createAvailabilityShouldReturn200Status() throws IOException {
        AvailabilityRepresentation representation = new AvailabilityRepresentation(TITLE, EMAIL, NAME, STATUS);
        Response response = endpointV1.createAvailability(representation);
        assertEquals(HTTP_STATUS_OK, response.getStatus());

    }


}

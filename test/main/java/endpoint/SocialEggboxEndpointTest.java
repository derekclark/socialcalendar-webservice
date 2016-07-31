package endpoint;

import database.DBAvailability;
import database.DBUser;
import database.InMemoryDBCreator;
import org.joda.time.DateTime;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import representation.availability.Availability;
import representation.availability.AvailabilityDAO;
import representation.availability.AvailabilityRepresentation;
import representation.user.User;
import representation.user.UserDAO;
import representation.user.UserRepresentation;
import utilities.JsonUtility;

import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.HashSet;
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

    public static final String STATUS = "status";
    public static final String TITLE = "title";
    public static final int NON_EXISTENT_ID = 9999;
    Set<User> sharedList;
    public static final DateTime START_DATE_TIME = new DateTime(2016,1,1,12,0);
    public static final DateTime END_DATE_TIME = new DateTime(2016,1,1,13,0);

    public static final String UNKNOWN_USER_ID = "unknownUserId";
    SocialEggboxEndpointV1 endpointV1;
    DBUser userRepo;
    DBAvailability availabilityRepo;

    User savedUser = new User(EMAIL, NAME, FACEBOOK_ID);
    User user = new User(EMAIL, NAME, FACEBOOK_ID);
    UserRepresentation userRepresentation;

    @Before
    public void setup(){
        userRepo = new InMemoryDBCreator().create(DBUser.class);
        availabilityRepo = new InMemoryDBCreator().create(DBAvailability.class);

        endpointV1 = new SocialEggboxEndpointV1(getUserDAO(),getAvailabilityDAO());
        userRepresentation = new UserRepresentation(user);

        Set<User> sharedList = new HashSet<User>();
        sharedList.add(new User(EMAIL, NAME, FACEBOOK_ID));

    }

    private void saveUser(User user){
        userRepo.create(user);
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
        Response response = endpointV1.saveUser(userRepresentation);
        assertEquals(HTTP_STATUS_OK, response.getStatus());
    }

    @Test
    public void saveUserShouldReturn400IfNoPayload() throws IOException {
        Response response = endpointV1.saveUser(null);
        assertEquals(HTTP_STATUS_BAD_REQUEST, response.getStatus());
    }

    @Test
    public void shouldReturnSavedUserPayload() throws IOException {
        Response response = endpointV1.saveUser(userRepresentation);
        assertEquals(user, response.getEntity());
    }

    @Test
    public void checkUserIsSaved() throws IOException {
        endpointV1.saveUser(userRepresentation);
        Response response = endpointV1.getUserById(EMAIL);
        assertEquals(HTTP_STATUS_OK, response.getStatus());
        String expectedPayload = new JsonUtility().toJson(user);
        assertEquals(expectedPayload, response.getEntity());
    }

    @Test
    public void deleteExistingUserShouldReturn200Status(){
        saveUser(savedUser);
        Response response = endpointV1.deleteUser(EMAIL);
        assertEquals(HTTP_STATUS_OK, response.getStatus());
    }

    @Test
    public void deleteNonExistingUserShouldReturn404Status(){
        Response response = endpointV1.deleteUser(EMAIL);
        assertEquals(HTTP_STATUS_NOT_FOUND, response.getStatus());
    }

    @Test
    public void createAvailabilityShouldReturn200Status() throws IOException {
        Response response = createAvailability();
        assertEquals(HTTP_STATUS_OK, response.getStatus());
    }

    @Test
    public void createAvailabilityShouldReturnAvailabilityInBody() throws IOException {
        Response response = createAvailability();
        Availability expectedAvailability = new Availability(getIdFromResponse(response),TITLE,EMAIL,NAME,STATUS);
        AvailabilityRepresentation expectedRepresentation = new AvailabilityRepresentation(expectedAvailability);
        assertEquals(expectedRepresentation, response.getEntity());
    }

    @Test
    public void createAvailabilityWithEmptyPayloadShouldReturn400Status() throws IOException {
        Response response = endpointV1.createAvailability(null);
        assertEquals(HTTP_STATUS_BAD_REQUEST, response.getStatus());
    }

    @Test
    public void getAvailabilityShouldReturn200StatusIfExists() throws IOException {
        Response response = createAvailability();
        response = endpointV1.getAvailabilityById(getIdFromResponse(response));
        assertEquals(HTTP_STATUS_OK, response.getStatus());
    }

    @Test
    public void getAvailabilityShouldReturn400StatusIfNotExists() throws IOException {
        createAvailability();
        Response response = endpointV1.getAvailabilityById(NON_EXISTENT_ID);
        assertEquals(HTTP_STATUS_NOT_FOUND, response.getStatus());
    }

    private int getIdFromResponse(Response response) {
        AvailabilityRepresentation representation = (AvailabilityRepresentation) response.getEntity();
        return representation.getId();
    }

    private Response createAvailability() throws IOException {
        AvailabilityRepresentation representation = new AvailabilityRepresentation(TITLE, EMAIL, NAME, STATUS,
                sharedList, START_DATE_TIME, END_DATE_TIME);
        return endpointV1.createAvailability(representation);
    }

}

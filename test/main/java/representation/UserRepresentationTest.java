package representation;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class UserRepresentationTest {
    public static final String EMAIL = "email";
    public static final String NAME = "name";
    public static final String FACEBOOK_ID = "facebookId";
    public static final String JSON_STRING= "{\n" +
            "  \"email\" : \"" + EMAIL + "\",\n" +
            "  \"name\" : \""+NAME+"\",\n" +
            "  \"facebookId\" : \""+FACEBOOK_ID+"\"\n" +
            "}";

    User user = new User(EMAIL, NAME, FACEBOOK_ID);
    UserRepresentation representation;

    @Before
    public void setup(){
        representation = new UserRepresentation(user);
    }

    @Test
    public void canCreateRepresentation(){
        assertEquals(user,representation.getUser());
    }

    @Test
    public void canMarshallToJson() throws IOException {
        String expectedJson = JSON_STRING;
        assertEquals(expectedJson, representation.toJson());
    }

    @Test
    public void canUnMarshallToObject() throws IOException {
        String jsonString = JSON_STRING;
        User expectedUser = new User(EMAIL, NAME, FACEBOOK_ID);
        User actualUser = representation.unMarshallJson(jsonString);
        assertEquals(expectedUser, actualUser);
    }
}

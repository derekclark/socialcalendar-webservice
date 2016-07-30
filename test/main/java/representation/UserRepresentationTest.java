package representation;

import org.junit.Before;
import org.junit.Test;
import utilities.JsonUtility;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class UserRepresentationTest {
    public static final String EMAIL = "email";
    public static final String NAME = "name";
    public static final String FACEBOOK_ID = "facebookId";
    public static final String NEWLINE = "\n";

    User user;

    @Before
    public void setup(){
        user = new User(EMAIL, NAME, FACEBOOK_ID);
    }

    @Test
    public void returnsUser(){
        UserRepresentation userRepresentation = new UserRepresentation(EMAIL, NAME, FACEBOOK_ID);
        assertEquals(user,userRepresentation.asUser());
    }

    @Test
    public void buildRepresentationFromUserObject(){
        UserRepresentation userRepresentation = new UserRepresentation(user);
        assertEquals(user, userRepresentation.asUser());
    }

    @Test
    public void testsJsonRepresentation() throws IOException {
        String expectedJson = "{" + NEWLINE +
                "  \"email\" : \""+EMAIL+"\"," + NEWLINE +
                "  \"name\" : \""+NAME+"\"," + NEWLINE +
                "  \"facebookId\" : \""+FACEBOOK_ID+"\"" + NEWLINE +
                "}";
        UserRepresentation representation = new UserRepresentation(user);
        String actualJson = new JsonUtility().toJson(representation);
        assertEquals(expectedJson, actualJson);
    }
}

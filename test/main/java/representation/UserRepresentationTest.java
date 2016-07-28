package representation;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserRepresentationTest {

    public static final String EMAIL = "email";
    public static final String NAME = "name";
    public static final String FACEBOOK_ID = "facebookId";

    @Test
    public void returnsUser(){
        UserRepresentation userRepresentation = new UserRepresentation(EMAIL, NAME, FACEBOOK_ID);
        User user = new User(EMAIL, NAME, FACEBOOK_ID);
        assertEquals(user,userRepresentation.asUser());
    }

    @Test
    public void buildRepresentationFromUserObject(){
        User user = new User(EMAIL, NAME, FACEBOOK_ID);
        UserRepresentation userRepresentation = new UserRepresentation(user);
        assertEquals(user, userRepresentation.asUser());
    }

}

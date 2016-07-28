package representation;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserRepresentationTest {

    @Test
    public void returnsUser(){
        UserRepresentation userRepresentation = new UserRepresentation("email", "name", "facebookId");
        User user = new User("email", "name", "facebookId");
        assertEquals(user,userRepresentation.asUser());
    }
}

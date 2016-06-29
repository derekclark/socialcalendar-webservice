package representation;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserRepresentationTest {
    @Test
    public void canCreateRepresentation(){
        UserRepresentation representation = new UserRepresentation("email","name","facebookId");
        assertEquals("email",representation.getEmail());
        assertEquals("name",representation.getName());
        assertEquals("facebookId",representation.getFacebookId());
    }
}

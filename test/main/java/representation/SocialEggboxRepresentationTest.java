package representation;

import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;

public class SocialEggboxRepresentationTest {
    SocialEggboxRepresentationV1 representation;

    @Before
    public void setup(){
        representation = new SocialEggboxRepresentationV1();
    }

    @Test
    public void getUserByIdWhichExistsShouldReturn200Status(){
        Response response = representation.getUserById("userid");
        assertEquals(200, response.getStatus());
    }

    @Test
    public void getUserByIdWhichExistsShouldReturnUserInBody(){
        Response response = representation.getUserById("userid");
        User user = new User("email","name","facebookId");
        assertEquals(user, response.getEntity());
    }

}

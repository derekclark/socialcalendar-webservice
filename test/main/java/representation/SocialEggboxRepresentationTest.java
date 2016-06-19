package representation;

import org.junit.Test;

import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;

public class SocialEggboxRepresentationTest {
    @Test
    public void getUserByIdWhichExistsShouldReturn200Status(){
        SocialEggboxRepresentationV1 representation = new SocialEggboxRepresentationV1();
        Response response = representation.getUserById("userid");
        assertEquals(200, response.getStatus());
    }
}

package representation;

import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class SocialEggboxRepresentationTest {
    SocialEggboxRepresentationV1 representation;

    @Before
    public void setup(){
        User user = new User("email","name","facebookId");
        UserDAO repo = new UserDAO();
        repo.save(user);

        representation = new SocialEggboxRepresentationV1(repo);
    }

    @Test
    public void getUserByIdWhichExistsShouldReturn200Status(){
        Response response = representation.getUserById("email");
        assertEquals(200, response.getStatus());
    }

    @Test
    public void getUserByIdWhichExistsShouldReturnUserInBody(){
        Response response = representation.getUserById("email");
        User user = new User("email","name","facebookId");
        assertEquals(user, response.getEntity());
    }

    @Test
    public void getUserByIdShouldReturn404IfNotFound(){
        Response response = representation.getUserById("unknownUserId");
        assertEquals(404, response.getStatus());
    }

    @Test
    public void getUserByIdShouldReturnEmptyBodyIfNotFound(){
        Response response = representation.getUserById("unknownUserId");
        assertNull(response.getEntity());
    }

}

package representation;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class UserTest {

    @Test
    public void canCreateUser(){
        User user = new User("email","name","facebookId");
        assertEquals("email",user.getEmail());
        assertEquals("name", user.getName());
        assertEquals("facebookId", user.getFacebookId());
    }

    @Test
    public void shouldBeEqual(){
        User user1 = new User("email","name","facebookId");
        User user2 = new User("email","name","facebookId");
        assertEquals(user1, user2);
    }

    @Test
    public void shouldNotBeEqual(){
        User user1 = new User("email","name","facebookId");
        User differentEmail = new User("differentEmail","name","facebookId");
        assertNotEquals(user1, differentEmail);

        User differentName = new User("email","differentName","facebookId");
        assertNotEquals(user1, differentName);

        User differentFacebookId = new User("email","name","differentFacebookId");
        assertNotEquals(user1, differentFacebookId);

    }

}

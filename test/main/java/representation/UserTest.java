package representation;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserTest {

    @Test
    public void canCreateUser(){
        User user = new User("email","name","facebookId");
        assertEquals("email",user.getEmail());
        assertEquals("name", user.getName());
        assertEquals("facebookId", user.getFacebookId());
    }
}

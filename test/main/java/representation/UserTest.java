package representation;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class UserTest {
    public static final String EMAIL = "email";
    public static final String NAME = "name";
    public static final String FACEBOOK_ID = "facebookId";
    public static final String DIFFERENT_EMAIL = "differentEmail";
    public static final String DIFFERENT_NAME = "differentName";
    public static final String DIFFERENT_FACEBOOK_ID = "differentFacebookId";

    @Test
    public void canCreateUser(){
        User user = new User(EMAIL, NAME, FACEBOOK_ID);
        assertEquals(EMAIL,user.getEmail());
        assertEquals(NAME, user.getName());
        assertEquals(FACEBOOK_ID, user.getFacebookId());
    }

    @Test
    public void shouldBeEqual(){
        User user1 = new User(EMAIL, NAME, FACEBOOK_ID);
        User user2 = new User(EMAIL, NAME, FACEBOOK_ID);
        assertEquals(user1, user2);
    }

    @Test
    public void shouldBeEqualIfSameObject(){
        User user1 = new User(EMAIL, NAME, FACEBOOK_ID);
        assertEquals(user1, user1);
    }

    @Test
    public void shouldNotBeEqual(){
        User user1 = new User(EMAIL, NAME, FACEBOOK_ID);
        User differentEmail = new User(DIFFERENT_EMAIL, NAME, FACEBOOK_ID);
        assertNotEquals(user1, differentEmail);

        User differentName = new User(EMAIL, DIFFERENT_NAME, FACEBOOK_ID);
        assertNotEquals(user1, differentName);

        User differentFacebookId = new User(EMAIL, NAME, DIFFERENT_FACEBOOK_ID);
        assertNotEquals(user1, differentFacebookId);
    }

    @Test
    public void shouldNotBeEqualIfComparingAgainstNull(){
        User user1 = new User(EMAIL, NAME, FACEBOOK_ID);
        assertNotEquals(user1,null);
    }

    @Test
    public void shouldNotBeEqualIfComparingDifferentClassObject(){
        User user1 = new User(EMAIL, NAME, FACEBOOK_ID);
        assertNotEquals(user1, new String(""));
    }

    @Test
    public void shouldHaveSameHashCodes(){
        User user = new User(EMAIL, NAME, FACEBOOK_ID);
        User sameValuesAsUser = new User(EMAIL, NAME, FACEBOOK_ID);
        assertEquals(user.hashCode(), user.hashCode());
        assertEquals(user.hashCode(), sameValuesAsUser.hashCode());
    }

    @Test
    public void shouldHaveDifferentHasCodes(){
        User user = new User(EMAIL, NAME, FACEBOOK_ID);
        User differentValues = new User(DIFFERENT_EMAIL, NAME, FACEBOOK_ID);
        assertNotEquals(user.hashCode(), differentValues.hashCode());
    }
}

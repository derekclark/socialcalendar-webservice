package representation;

import org.junit.Before;
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

    User user, userWithDifferentFacebookId, userWithDifferentEmail,
            userWithDifferentName, userWithSameValues;

    @Before
    public void setup(){
        user = new User(EMAIL, NAME, FACEBOOK_ID);
        userWithSameValues = new User(EMAIL, NAME, FACEBOOK_ID);
        userWithDifferentFacebookId = new User(EMAIL, NAME, DIFFERENT_FACEBOOK_ID);
        userWithDifferentEmail = new User(DIFFERENT_EMAIL, NAME, FACEBOOK_ID);
        userWithDifferentName = new User(EMAIL, DIFFERENT_NAME, FACEBOOK_ID);
    }

    @Test
    public void canCreateUser(){
        assertEquals(EMAIL,user.getEmail());
        assertEquals(NAME, user.getName());
        assertEquals(FACEBOOK_ID, user.getFacebookId());
    }

    @Test
    public void shouldBeEqualIfObjectsHaveSameValues(){
        assertEquals(user, userWithSameValues);
    }

    @Test
    public void shouldBeEqualIfSameObject(){
        assertEquals(user, user);
    }

    @Test
    public void shouldNotBeEqual(){
        assertNotEquals(user, userWithDifferentFacebookId);
        assertNotEquals(user, userWithDifferentEmail);
        assertNotEquals(user, userWithDifferentName);
    }

    @Test
    public void shouldNotBeEqualIfComparingAgainstNull(){
        assertNotEquals(user,null);
    }

    @Test
    public void shouldNotBeEqualIfComparingDifferentClassObject(){
        assertNotEquals(user, "");
    }

    @Test
    public void shouldHaveSameHashCodes(){
        assertEquals(user.hashCode(), user.hashCode());
        assertEquals(user.hashCode(), userWithSameValues.hashCode());
    }

    @Test
    public void shouldHaveDifferentHashCodes(){
        assertNotEquals(user.hashCode(), userWithDifferentEmail.hashCode());
        assertNotEquals(user.hashCode(), userWithDifferentFacebookId.hashCode());
        assertNotEquals(user.hashCode(), userWithDifferentName.hashCode());
    }
}

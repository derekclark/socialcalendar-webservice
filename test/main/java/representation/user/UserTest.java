package representation.user;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static representation.TestConstants.*;

public class UserTest {
    public static final String DIFFERENT_EMAIL = "differentEmail";
    public static final String DIFFERENT_NAME = "differentName";
    public static final String DIFFERENT_FACEBOOK_ID = "differentFacebookId";

    User user, userWithDifferentFacebookId, userWithDifferentEmail,
            userWithDifferentName, userWithSameValues;

    @Before
    public void setup(){
        user = new SampleUserBuilder().build();
        userWithSameValues = new SampleUserBuilder().build();
        userWithDifferentFacebookId = new SampleUserBuilder().withFacebookId(DIFFERENT_FACEBOOK_ID).build();
        userWithDifferentEmail = new SampleUserBuilder().withEmail(DIFFERENT_EMAIL).build();
        userWithDifferentName = new SampleUserBuilder().withName(DIFFERENT_NAME).build();
    }

    @Test
    public void canCreateUser(){
        assertEquals(EMAIL,user.getEmail());
        assertEquals(NAME, user.getName());
        assertEquals(FACEBOOKID, user.getFacebookId());
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

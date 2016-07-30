package representation;

import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class AvailabilityRepresentationTest {
    public static final String TITLE = "title";
    public static final String EMAIL = "email";
    public static final String NAME = "name";
    public static final String STATUS = "status";
    public static final String FACEBOOK_ID = "facebookId";
    AvailabilityRepresentation representation;

    Set<User> sharedList = new HashSet<>();

    @Before
    public void setup(){
        Availability availability = new Availability(TITLE, EMAIL, NAME, STATUS, sharedList);
        representation = new AvailabilityRepresentation(availability);
    }

    @Test
    public void returnsAvailability(){
        Availability availability = representation.asAvailability();
        Availability expectedAvailability = new Availability(TITLE,
                EMAIL,NAME, STATUS);
        assertEquals(expectedAvailability, availability);
    }

    @Test
    public void buildRepresentationFromAvailabilityObject(){
        Availability availability = new Availability(TITLE, EMAIL, NAME, STATUS);
        AvailabilityRepresentation availabilityRepresentation = new AvailabilityRepresentation(availability);
        assertEquals(availability, availabilityRepresentation.asAvailability());
    }

    @Test
    public void shouldBeEqual(){
        AvailabilityRepresentation sameContent = new AvailabilityRepresentation(TITLE, EMAIL,
                NAME, STATUS, sharedList);
        assertEquals(sameContent, representation);
        assertEquals(representation, representation);
    }

    @Test
    public void shouldNotBeEqual(){
        AvailabilityRepresentation differentTitle = new AvailabilityRepresentation("", EMAIL,
                NAME, STATUS, sharedList);
        AvailabilityRepresentation differentEmail = new AvailabilityRepresentation(TITLE, "",
                NAME, STATUS, sharedList);
        AvailabilityRepresentation differentName = new AvailabilityRepresentation(TITLE, EMAIL,
                "", STATUS, sharedList);
        AvailabilityRepresentation differentStatus = new AvailabilityRepresentation(TITLE, EMAIL,
                NAME, "", sharedList);

        Set<User> differentSharedList = new HashSet<User>();
        differentSharedList.add(new User(EMAIL, NAME, FACEBOOK_ID));
        differentSharedList.add(new User("another email", NAME, FACEBOOK_ID));

        AvailabilityRepresentation differentSharedUsers = new AvailabilityRepresentation(TITLE, EMAIL,
                NAME, STATUS, differentSharedList);

        assertNotEquals(differentTitle, representation);
        assertNotEquals(differentEmail, representation);
        assertNotEquals(differentName, representation);
        assertNotEquals(differentStatus, representation);
        assertNotEquals(differentSharedUsers, representation);
        assertNotEquals("", representation);
        assertNotEquals(null, representation);
    }

}

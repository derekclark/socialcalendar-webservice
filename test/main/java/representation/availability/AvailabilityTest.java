package representation.availability;


import org.junit.Before;
import org.junit.Test;
import representation.user.User;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static representation.availability.SampleAvailabilityBuilder.*;

public class AvailabilityTest {
    public static final String DIFFERENT_TITLE = "different";
    public static final String DIFFERENT_STATUS = "different";
    public static final String DIFFERENT_OWNER_EMAIL = "different";
    public static final String DIFFERENT_OWNER_NAME = "different";
    public static final String FACEBOOK_ID = "facebookId";
    public static final String ANOTHER_EMAIL = "another email";

    Availability availability;
    Availability sameValues;
    Availability differentTitle, differentStatus, differentOwnerName, differentOwnerEmail,
            differentSharedUsers, differentStartDate, differentEndDate;
    Set<User> sharedList, differentSharedList;

    @Before
    public void setup(){
        sharedList = new HashSet<User>();
        differentSharedList = new HashSet<User>();
        differentSharedList.add(new User(EMAIL, NAME, FACEBOOK_ID));
        differentSharedList.add(new User(ANOTHER_EMAIL, NAME, FACEBOOK_ID));

        availability = getAvailability();
        sameValues = getAvailability();
        differentTitle = new SampleAvailabilityBuilder().withTitle(DIFFERENT_TITLE).build();
        differentOwnerEmail = new SampleAvailabilityBuilder().withOwnerEmail(DIFFERENT_OWNER_EMAIL).build();
        differentOwnerName = new SampleAvailabilityBuilder().withOwnerName(DIFFERENT_OWNER_NAME).build();
        differentStatus = new SampleAvailabilityBuilder().withStatus(DIFFERENT_STATUS).build();
        differentSharedUsers = new SampleAvailabilityBuilder().withSharedUsers(differentSharedList).build();
        differentStartDate = new SampleAvailabilityBuilder().withStartDateTime(null).build();
        differentEndDate = new SampleAvailabilityBuilder().withEndDateTime(null).build();
    }

    private Availability getAvailability() {
        return new SampleAvailabilityBuilder().build();
    }

    @Test
    public void canCreateAvailability(){
        assertEquals(STATUS, availability.getStatus());
        assertEquals(EMAIL, availability.getOwnerEmail());
        assertEquals(NAME, availability.getOwnerName());
        assertEquals(TITLE, availability.getTitle());
        assertEquals(sharedList, availability.getSharedWithUsers());
        assertEquals(START_DATE_TIME, availability.getStartDateTime());
        assertEquals(END_DATE_TIME, availability.getEndDateTime());
    }

    @Test
    public void shouldBeEqualForSameObject(){
        Availability availabilty = new Availability(TITLE, EMAIL, NAME, STATUS, sharedList,
                START_DATE_TIME, END_DATE_TIME);
        assertEquals(availabilty, availabilty);
    }

    @Test
    public void shouldBeEqualForSameContent(){
        assertEquals(availability, sameValues);
    }

    @Test
    public void shouldNotBeEqual(){
        assertNotEquals(availability, differentTitle);
        assertNotEquals(availability, differentOwnerEmail);
        assertNotEquals(availability, differentOwnerName);
        assertNotEquals(availability, differentTitle);
        assertNotEquals(availability, differentSharedUsers);
        assertNotEquals(availability, differentStartDate);
        assertNotEquals(availability, differentEndDate);
    }

    @Test
    public void shouldNotBeEqualIfComparingAgainstNull(){
        assertNotEquals(availability, null);
    }

    @Test
    public void shouldNotBeEqualIfComparingAgainstDifferentClass(){
        assertNotEquals(availability, "");
    }

    @Test
    public void shouldHaveSameHashCodes(){
        assertEquals(availability.hashCode(), availability.hashCode());
        assertEquals(availability.hashCode(), sameValues.hashCode());
    }

    @Test
    public void shouldHaveDifferentHashCodes(){
        assertNotEquals(availability.hashCode(), differentOwnerName.hashCode());
        assertNotEquals(availability.hashCode(), differentStatus.hashCode());
        assertNotEquals(availability.hashCode(), differentOwnerEmail.hashCode());
        assertNotEquals(availability.hashCode(), differentTitle.hashCode());
        assertNotEquals(availability.hashCode(), differentSharedList.hashCode());
        assertNotEquals(availability.hashCode(), differentStartDate.hashCode());
        assertNotEquals(availability.hashCode(), differentEndDate.hashCode());
    }
}

package representation.availability;


import org.junit.Before;
import org.junit.Test;
import representation.TestConstants;
import representation.user.User;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

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
    Set<User> sharedList=null;
    Set<User> differentSharedList;

    @Before
    public void setup(){
        differentSharedList = new HashSet<User>();
        differentSharedList.add(new User(TestConstants.EMAIL, TestConstants.NAME, FACEBOOK_ID));
        differentSharedList.add(new User(ANOTHER_EMAIL, TestConstants.NAME, FACEBOOK_ID));

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
        assertEquals(TestConstants.STATUS, availability.getStatus());
        assertEquals(TestConstants.EMAIL, availability.getOwnerEmail());
        assertEquals(TestConstants.NAME, availability.getOwnerName());
        assertEquals(TestConstants.TITLE, availability.getTitle());
        assertEquals(sharedList, availability.getSharedWithUsers());
        assertEquals(TestConstants.START_DATE_TIME, availability.getStartDateTime());
        assertEquals(TestConstants.END_DATE_TIME, availability.getEndDateTime());
    }

    @Test
    public void shouldBeEqualForSameObject(){
        Availability availabilty = new Availability(TestConstants.TITLE, TestConstants.EMAIL, TestConstants.NAME, TestConstants.STATUS, sharedList,
                TestConstants.START_DATE_TIME, TestConstants.END_DATE_TIME);
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

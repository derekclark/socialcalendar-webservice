package representation;


import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class AvailabilityTest {
    public static final String STATUS = "status";
    public static final String OWNER_NAME = "ownerName";
    public static final String OWNER_EMAIL = "ownerEmail";
    public static final String TITLE = "title";
    public static final String DIFFERENT_TITLE = "different";
    public static final String DIFFERENT_STATUS = "different";
    public static final String DIFFERENT_OWNER_EMAIL = "different";
    public static final String DIFFERENT_OWNER_NAME = "different";
    public static final DateTime START_DATE_TIME = new DateTime(2016,1,1,12,0);

    Availability availability;
    Availability sameValues;
    Availability differentTitle, differentStatus, differentOwnerName, differentOwnerEmail,
            differentSharedUsers, differentStartDate;
    Set<User> sharedList, differentSharedList;

    @Before
    public void setup(){
        sharedList = new HashSet<User>();
        differentSharedList = new HashSet<User>();
        differentSharedList.add(new User("email", "name", "facebookId"));
        differentSharedList.add(new User("another email", "name", "facebookId"));

        availability = new Availability(TITLE, OWNER_EMAIL, OWNER_NAME, STATUS, sharedList, START_DATE_TIME);
        sameValues = new Availability(TITLE, OWNER_EMAIL, OWNER_NAME, STATUS, sharedList, START_DATE_TIME);
        differentTitle = new Availability(DIFFERENT_TITLE, OWNER_EMAIL, OWNER_NAME, STATUS, sharedList, START_DATE_TIME);
        differentOwnerEmail = new Availability(TITLE,DIFFERENT_OWNER_EMAIL, OWNER_NAME, STATUS, sharedList, START_DATE_TIME);
        differentOwnerName = new Availability(TITLE, OWNER_EMAIL, DIFFERENT_OWNER_NAME, STATUS, sharedList, START_DATE_TIME);
        differentStatus = new Availability(TITLE, OWNER_EMAIL, OWNER_NAME, DIFFERENT_STATUS, sharedList, START_DATE_TIME);
        differentSharedUsers = new Availability(TITLE, OWNER_EMAIL, OWNER_NAME, STATUS, differentSharedList, START_DATE_TIME);
        differentStartDate = new Availability(TITLE, OWNER_EMAIL, OWNER_NAME, STATUS, sharedList, null);
    }

    @Test
    public void canCreateAvailability(){
        assertEquals(availability.getStatus(),STATUS);
        assertEquals(availability.getOwnerEmail(),OWNER_EMAIL);
        assertEquals(availability.getOwnerName(),OWNER_NAME);
        assertEquals(availability.getTitle(),TITLE);
        assertEquals(availability.getSharedWithUsers(),sharedList);
        assertEquals(availability.getStartDateTime(), START_DATE_TIME);
    }

    @Test
    public void shouldBeEqualForSameObject(){
        Availability availabilty = new Availability(TITLE, OWNER_EMAIL, OWNER_NAME, STATUS, sharedList, START_DATE_TIME);
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
    }
}

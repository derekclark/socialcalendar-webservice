package representation;


import org.joda.time.LocalDateTime;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class AvailabilityTest {
    public static final LocalDateTime START_DATE = new LocalDateTime(2015, 1, 2, 0, 0, 0);
    public static final LocalDateTime END_DATE = new LocalDateTime(2015, 1, 2, 1, 2, 30);
    public static final String STATUS = "status";
    public static final String OWNER_NAME = "ownerName";
    public static final String OWNER_EMAIL = "ownerEmail";
    public static final String TITLE = "title";
    public static final String DIFFERENT_TITLE = "different";
    public static final String DIFFERENT_STATUS = "different";
    public static final String DIFFERENT_OWNER_EMAIL = "different";
    public static final String DIFFERENT_OWNER_NAME = "different";
    public static final LocalDateTime DIFFERENT_START_DATE = new LocalDateTime(2000, 1, 2, 0, 0, 0);
    public static final LocalDateTime DIFFERENT_END_DATE = new LocalDateTime(2000, 1, 2, 0, 0, 0);

    Availability availability;
    Availability availabilityWithSameValues;
    Availability availabilityWithDifferentTitle, availabilityWithDifferentStartDate, availabilityWithDifferentEndDate,
            availabilityWithDifferentStatus, availabilityWithDifferentOwnerName, availabilityWithDifferentOwnerEmail,
            availabilityWithDifferentSharedList;
    Set<User> sharedList, differentSharedList;

    @Before
    public void setup(){
        sharedList = new HashSet<User>();
        differentSharedList = new HashSet<User>();
        differentSharedList.add(new User("email","name","facebookId"));
        differentSharedList.add(new User("another email","name","facebookId"));

        availability = new Availability(TITLE,START_DATE,
                END_DATE, OWNER_EMAIL, OWNER_NAME, STATUS,sharedList);
        availabilityWithSameValues = new Availability(TITLE,START_DATE,
                END_DATE, OWNER_EMAIL, OWNER_NAME, STATUS,sharedList);
        availabilityWithDifferentTitle = new Availability(DIFFERENT_TITLE,START_DATE,
                END_DATE, OWNER_EMAIL, OWNER_NAME, STATUS,sharedList);
        availabilityWithDifferentEndDate = new Availability(TITLE,START_DATE,
                DIFFERENT_END_DATE, OWNER_EMAIL, OWNER_NAME, STATUS,sharedList);
        availabilityWithDifferentStartDate = new Availability(TITLE,DIFFERENT_START_DATE,
                END_DATE, OWNER_EMAIL, OWNER_NAME, STATUS,sharedList);
        availabilityWithDifferentOwnerEmail = new Availability(TITLE,START_DATE,
                END_DATE, DIFFERENT_OWNER_EMAIL, OWNER_NAME, STATUS,sharedList);
        availabilityWithDifferentOwnerName = new Availability(TITLE,START_DATE,
                END_DATE, OWNER_EMAIL, DIFFERENT_OWNER_NAME, STATUS,sharedList);
        availabilityWithDifferentSharedList = new Availability(TITLE,START_DATE,
                END_DATE, OWNER_EMAIL, OWNER_NAME, STATUS, differentSharedList);
        availabilityWithDifferentStatus = new Availability(TITLE,START_DATE,
                END_DATE, OWNER_EMAIL, OWNER_NAME, DIFFERENT_STATUS,sharedList);
    }

    @Test
    public void canCreateAvailability(){
        assertEquals(availability.getStatus(),STATUS);
        assertEquals(availability.getSharedList(),sharedList);
        assertEquals(availability.getStartDate(),START_DATE);
        assertEquals(availability.getEndDate(),END_DATE);
        assertEquals(availability.getOwnerEmail(),OWNER_EMAIL);
        assertEquals(availability.getOwnerName(),OWNER_NAME);
        assertEquals(availability.getTitle(),TITLE);
    }

    @Test
    public void shouldBeEqualForSameObject(){
        Availability availabilty = new Availability(TITLE,START_DATE,
                END_DATE, OWNER_EMAIL, OWNER_NAME, STATUS,sharedList);
        assertEquals(availabilty, availabilty);
    }

    @Test
    public void shouldBeEqualForSameContent(){
        assertEquals(availability, availabilityWithSameValues);
    }

    @Test
    public void shouldNotBeEqual(){
        assertNotEquals(availability, availabilityWithDifferentTitle);
        assertNotEquals(availability, availabilityWithDifferentEndDate);
        assertNotEquals(availability, availabilityWithDifferentStartDate);
        assertNotEquals(availability, availabilityWithDifferentOwnerEmail);
        assertNotEquals(availability, availabilityWithDifferentOwnerName);
        assertNotEquals(availability, availabilityWithDifferentSharedList);
        assertNotEquals(availability, availabilityWithDifferentTitle);
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
        assertEquals(availability.hashCode(), availabilityWithSameValues.hashCode());
    }

    @Test
    public void shouldHaveDifferentHashCodes(){
        assertNotEquals(availability.hashCode(), availabilityWithDifferentStartDate.hashCode());
        assertNotEquals(availability.hashCode(), availabilityWithDifferentOwnerName.hashCode());
        assertNotEquals(availability.hashCode(), availabilityWithDifferentEndDate.hashCode());
        assertNotEquals(availability.hashCode(), availabilityWithDifferentStatus.hashCode());
        assertNotEquals(availability.hashCode(), availabilityWithDifferentSharedList.hashCode());
        assertNotEquals(availability.hashCode(), availabilityWithDifferentOwnerEmail.hashCode());
        assertNotEquals(availability.hashCode(), availabilityWithDifferentTitle.hashCode());
    }
}

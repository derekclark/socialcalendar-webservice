package representation;


import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class AvailabilityTest {
    public static final LocalDateTime START_DATE = LocalDateTime.now();
    public static final LocalDateTime END_DATE = LocalDateTime.now();
    public static final String STATUS = "status";
    public static final String OWNER_NAME = "ownerName";
    public static final String OWNER_EMAIL = "ownerEmail";
    public static final String TITLE = "title";
    public static final String DIFFERENT_TITLE = "different";
    public static final String DIFFERENT_STATUS = "different";
    public static final String DIFFERENT_OWNER_EMAIL = "different";
    public static final String DIFFERENT_OWNER_NAME = "different";
    public static final LocalDateTime DIFFERENT_START_DATE = LocalDateTime.of(2015, 1, 2, 0, 0, 0);
    public static final LocalDateTime DIFFERENT_END_DATE = LocalDateTime.of(2000, 1, 2, 0, 0, 0);

    Availability availability;
    Availability sameValues;
    Availability differentTitle, differentStatus, differentOwnerName, differentOwnerEmail,
            differentSharedUsers;
    Set<User> sharedList, differentSharedList;

    @Before
    public void setup(){
        sharedList = new HashSet<User>();
        differentSharedList = new HashSet<User>();
        differentSharedList.add(new User("email", "name", "facebookId"));
        differentSharedList.add(new User("another email", "name", "facebookId"));

        Date dt = new Date();
        LocalDateTime.ofInstant(dt.toInstant(), ZoneId.systemDefault());

        availability = new Availability(TITLE, OWNER_EMAIL, OWNER_NAME, STATUS, sharedList);
        sameValues = new Availability(TITLE, OWNER_EMAIL, OWNER_NAME, STATUS, sharedList);
        differentTitle = new Availability(DIFFERENT_TITLE, OWNER_EMAIL, OWNER_NAME, STATUS, sharedList);
        differentOwnerEmail = new Availability(TITLE,DIFFERENT_OWNER_EMAIL, OWNER_NAME, STATUS, sharedList);
        differentOwnerName = new Availability(TITLE, OWNER_EMAIL, DIFFERENT_OWNER_NAME, STATUS, sharedList);
        differentStatus = new Availability(TITLE, OWNER_EMAIL, OWNER_NAME, DIFFERENT_STATUS, sharedList);
        differentSharedUsers = new Availability(TITLE, OWNER_EMAIL, OWNER_NAME, STATUS, differentSharedList);
    }

    @Test
    public void canCreateAvailability(){
        assertEquals(availability.getStatus(),STATUS);
        assertEquals(availability.getOwnerEmail(),OWNER_EMAIL);
        assertEquals(availability.getOwnerName(),OWNER_NAME);
        assertEquals(availability.getTitle(),TITLE);
        assertEquals(availability.getSharedWithUsers(),sharedList);
    }

    @Test
    public void shouldBeEqualForSameObject(){
        Availability availabilty = new Availability(TITLE, OWNER_EMAIL, OWNER_NAME, STATUS, sharedList);
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
    }
}

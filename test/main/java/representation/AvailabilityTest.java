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

    Availability availability;
    Availability availabilityWithSameValues;
    Availability availabilityWithDifferentValues;
    Set<User> sharedList;

    @Before
    public void setup(){
        sharedList = new HashSet<User>();
        availability = new Availability(TITLE,START_DATE,
                END_DATE, OWNER_EMAIL, OWNER_NAME, STATUS,sharedList);
        availabilityWithSameValues = new Availability(TITLE,START_DATE,
                END_DATE, OWNER_EMAIL, OWNER_NAME, STATUS,sharedList);
        availabilityWithDifferentValues = new Availability("different Title",START_DATE,
                END_DATE, OWNER_EMAIL, OWNER_NAME, STATUS,sharedList);
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
        assertNotEquals(availability, availabilityWithDifferentValues);
    }

    @Test
    public void shouldNotBeEqualIfComparingAgainstNull(){
        assertNotEquals(availability, null);
    }

    @Test
    public void shouldNotBeEqualIfComparingAgainstDifferentClass(){
        assertNotEquals(availability, "");
    }
}

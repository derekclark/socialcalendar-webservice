package representation;

import org.joda.time.LocalDateTime;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class AvailabilityTest {
    public static final LocalDateTime START_DATE = new LocalDateTime(2015, 1, 2, 0, 0, 0);
    public static final LocalDateTime END_DATE = new LocalDateTime(2015, 1, 2, 1, 2, 30);
    public static final String STATUS = "status";
    public static final String OWNER_NAME = "ownerName";
    public static final String OWNER_EMAIL = "ownerEmail";
    public static final String TITLE = "title";

    @Test
    public void canCreateAvailability(){
        Set<User> sharedList = new HashSet<User>();
        Availability availabilty = new Availability(TITLE,START_DATE,
                END_DATE, OWNER_EMAIL, OWNER_NAME, STATUS,sharedList);

        assertEquals(availabilty.getStatus(),STATUS);
        assertEquals(availabilty.getSharedList(),sharedList);
        assertEquals(availabilty.getStartDate(),START_DATE);
        assertEquals(availabilty.getEndDate(),END_DATE);
        assertEquals(availabilty.getOwnerEmail(),OWNER_EMAIL);
        assertEquals(availabilty.getOwnerName(),OWNER_NAME);
        assertEquals(availabilty.getTitle(),TITLE);

    }
}

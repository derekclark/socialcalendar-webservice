package representation;

import database.DBAvailability;
import database.InMemoryDBCreator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertNotNull;

public class AvailabilityDAOTest {
    DBAvailability repo;
    Availability availability;
    AvailabilityDAO availabilityDAO;
    public static final LocalDateTime START_DATE = LocalDateTime.now();
    public static final LocalDateTime END_DATE = LocalDateTime.now();
    public static final String STATUS = "status";
    public static final String OWNER_NAME = "ownerName";
    public static final String OWNER_EMAIL = "ownerEmail";
    public static final String TITLE = "title";

    @Before
    public void setup(){
        repo = new InMemoryDBCreator().create(DBAvailability.class);
        Set<User> userSharedList = new HashSet<User>();
        userSharedList.add(new User("email", "name", "facebookId"));
        userSharedList.add(new User("another email", "name", "facebookId"));
        availability = new Availability(TITLE,START_DATE,
                END_DATE, OWNER_EMAIL, OWNER_NAME, STATUS, userSharedList);
        availabilityDAO = new AvailabilityDAO(repo);
    }

    @After
    public void cleanup(){
        new InMemoryDBCreator().clean();
    }

    @Test
    public void canSaveAvailability(){
        assertNotNull(availabilityDAO.save(availability));
    }

}

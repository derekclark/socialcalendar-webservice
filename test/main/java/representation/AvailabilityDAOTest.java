package representation;

import database.DBUser;
import database.InMemoryDBCreator;
import org.joda.time.LocalDateTime;
import org.junit.Before;

import java.util.HashSet;
import java.util.Set;

public class AvailabilityDAOTest {
    DBUser repo;
    Availability availability;
    AvailabilityDAO availbilityDAO;
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

    @Before
    public void setup(){
        repo = new InMemoryDBCreator().create(DBUser.class);
        Set<User> userSharedList = new HashSet<User>();
        userSharedList.add(new User("email", "name", "facebookId"));
        userSharedList.add(new User("another email", "name", "facebookId"));
        availability = new Availability(TITLE,START_DATE,
                END_DATE, OWNER_EMAIL, OWNER_NAME, STATUS, userSharedList);

        AvailabilityDAO = new AvailabilityDAO(repo);
    }


}

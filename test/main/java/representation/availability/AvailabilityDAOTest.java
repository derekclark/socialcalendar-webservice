package representation.availability;

import database.DBAvailability;
import database.InMemoryDBCreator;
import org.joda.time.DateTime;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class AvailabilityDAOTest {
    public static final String FACEBOOK_ID = "facebookId";
    public static final String ANOTHER_EMAIL = "another email";
    DBAvailability repo;
    Availability availability;
    AvailabilityDAO availabilityDAO;
    public static final String STATUS = "status";
    public static final String NAME = "ownerName";
    public static final String EMAIL = "ownerEmail";
    public static final String TITLE = "title";
    public static final DateTime START_DATE_TIME = new DateTime(2016,1,1,12,30,0);
    public static final DateTime END_DATE_TIME = new DateTime(2016,1,1,13,45,0);

    @Before
    public void setup(){
        repo = new InMemoryDBCreator().create(DBAvailability.class);
        availability = new Availability(TITLE, EMAIL, NAME, STATUS,null,START_DATE_TIME,END_DATE_TIME);
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

    @Test
    public void canRetrieveAvailabilityById(){
        int id = availabilityDAO.save(availability);
        Availability retrievedAvailability = availabilityDAO.read(id);
        assertEquals(availability, retrievedAvailability);
    }

    @Test
    public void canDeleteAvailability(){
        int id = availabilityDAO.save(availability);
        System.out.println(availabilityDAO.deleteById(id));
        Availability availability = availabilityDAO.read(id);
        assertNull(availability);
    }

}

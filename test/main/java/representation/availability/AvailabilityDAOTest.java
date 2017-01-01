package representation.availability;

import database.DBAvailability;
import database.InMemoryDBCreator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import representation.TestConstants;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class AvailabilityDAOTest {
    public static final String ANOTHER_EMAIL = "not me";
    private DBAvailability repo;
    private Availability availability;
    private AvailabilityDAO availabilityDAO;

    @Before
    public void setup(){
        repo = new InMemoryDBCreator().create(DBAvailability.class);
        availability = new SampleAvailabilityBuilder().build();
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
        availability.setId(id);
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

    @Test
    public void returnsAllMyAvailabilities(){
        List<Availability> expectedListOfMyAvailabilities = new ArrayList<Availability>();
        expectedListOfMyAvailabilities.add(saveAvailabilityWithOwner(TestConstants.EMAIL));
        saveAvailabilityWithOwner(ANOTHER_EMAIL);
        expectedListOfMyAvailabilities.add(saveAvailabilityWithOwner(TestConstants.EMAIL));

        List<Availability> actualList = availabilityDAO.getMyAvailabilities(TestConstants.EMAIL);
        assertEquals(expectedListOfMyAvailabilities, actualList);
    }

    private Availability saveAvailabilityWithOwner(String owner) {
        Availability availability = new SampleAvailabilityBuilder().withOwnerEmail(owner).build();
        int id = availabilityDAO.save(availability);
        availability.setId(id);
        return availability;
    }
}

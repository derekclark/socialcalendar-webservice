package representation;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class AvailabilityRepresentationTest {
    public static final String TITLE = "title";
    public static final String EMAIL = "email";
    public static final String NAME = "name";
    public static final String STATUS = "status";
    AvailabilityRepresentation representation;

    @Before
    public void setup(){
        representation = new AvailabilityRepresentation(TITLE, EMAIL,
                NAME, STATUS);

    }

    @Test
    public void returnsAvailability(){
        Availability availability = representation.asAvailability();
        Availability expectedAvailability = new Availability(TITLE,
                EMAIL,NAME, STATUS);
        assertEquals(expectedAvailability, availability);
    }

    @Test
    public void buildRepresentationFromAvailabilityObject(){
        Availability availability = new Availability(TITLE, EMAIL, NAME, STATUS);
        AvailabilityRepresentation availabilityRepresentation = new AvailabilityRepresentation(availability);
        assertEquals(availability, availabilityRepresentation.asAvailability());
    }

    @Test
    public void shouldBeEqual(){
        AvailabilityRepresentation sameContent = new AvailabilityRepresentation(TITLE, EMAIL,
                NAME, STATUS);
        assertEquals(sameContent, representation);
        assertEquals(representation, representation);
    }

    @Test
    public void shouldNotBeEqual(){
        AvailabilityRepresentation differentTitle = new AvailabilityRepresentation("", EMAIL,
                NAME, STATUS);
        AvailabilityRepresentation differentEmail = new AvailabilityRepresentation(TITLE, "",
                NAME, STATUS);
        AvailabilityRepresentation differentName = new AvailabilityRepresentation(TITLE, EMAIL,
                "", STATUS);
        AvailabilityRepresentation differentStatus = new AvailabilityRepresentation(TITLE, EMAIL,
                NAME, "");

        assertNotEquals(differentTitle, representation);
        assertNotEquals(differentEmail, representation);
        assertNotEquals(differentName, representation);
        assertNotEquals(differentStatus, representation);
        assertNotEquals("", representation);
        assertNotEquals(null, representation);
    }

}

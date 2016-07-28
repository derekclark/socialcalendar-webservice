package representation;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AvailabilityRepresentationTest {
    public static final String TITLE = "title";
    public static final String EMAIL = "email";
    public static final String NAME = "name";
    public static final String STATUS = "status";

    @Test
    public void returnsAvailability(){
        AvailabilityRepresentation representation = new AvailabilityRepresentation(TITLE, EMAIL,
                NAME, STATUS);

        Availability availability = representation.asAvailability();
        Availability expectedAvailability = new Availability(TITLE,
                EMAIL,NAME, STATUS);
        assertEquals(expectedAvailability, availability);
    }
}

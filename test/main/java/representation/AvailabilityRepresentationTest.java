package representation;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AvailabilityRepresentationTest {

    @Test
    public void returnsAvailability(){
        AvailabilityRepresentation representation = new AvailabilityRepresentation("title", "email",
                "name",
                "status");

        Availability availability = representation.asAvailability();
        Availability expectedAvailability = new Availability("title",
                "email",
                "name",
                "status");
        assertEquals(expectedAvailability, availability);
    }
}

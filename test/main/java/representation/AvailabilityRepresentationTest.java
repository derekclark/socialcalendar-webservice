package representation;

import org.junit.Test;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class AvailabilityRepresentationTest {

    @Test
    public void returnsAvailability(){
        Set<User> sharedList = new HashSet<>();
        LocalDateTime startDate = LocalDateTime.now();
        LocalDateTime endDate = LocalDateTime.now();

        AvailabilityRepresentation representation = new AvailabilityRepresentation("title",
                startDate,
                endDate,
                "email",
                "name",
                "status",
                sharedList);

        Availability availability = representation.asAvailability();
        Availability expectedAvailability = new Availability("title",
                startDate,
                endDate,
                "email",
                "name",
                "status",
                sharedList);
        assertEquals(expectedAvailability, availability);
    }
}

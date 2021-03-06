package representation.availability;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Before;
import org.junit.Test;
import representation.TestConstants;
import representation.user.User;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static utilities.JsonUtility.toJson;

public class AvailabilityRepresentationTest {
    public static final String FACEBOOK_ID = "facebookId";

    public static final String NEWLINE = "\n";
    private AvailabilityRepresentation representation;
    private Availability availability;

    Set<User> sharedList = new HashSet<>();

    @Before
    public void setup(){
        availability = new SampleAvailabilityBuilder().build();
        representation = new AvailabilityRepresentation(availability);
    }

    @Test
    public void returnsAvailability(){
        Availability availability = representation.asAvailability();
        Availability expectedAvailability = new SampleAvailabilityBuilder().build();
        assertEquals(expectedAvailability, availability);
    }

    @Test
    public void buildRepresentationFromAvailabilityObject(){
        AvailabilityRepresentation availabilityRepresentation = new AvailabilityRepresentation(availability);
        assertEquals(availability, availabilityRepresentation.asAvailability());
    }

    @Test
    public void shouldBeEqual(){
        AvailabilityRepresentation sameContent = new AvailabilityRepresentation(availability);
        assertEquals(sameContent, representation);
        assertEquals(representation, representation);
    }

    @Test
    public void shouldNotBeEqual(){
        AvailabilityRepresentation differentTitle =
                new AvailabilityRepresentation(new SampleAvailabilityBuilder().withTitle("").build());
        AvailabilityRepresentation differentEmail =
                new AvailabilityRepresentation(new SampleAvailabilityBuilder().withOwnerEmail("").build());
        AvailabilityRepresentation differentName =
                new AvailabilityRepresentation(new SampleAvailabilityBuilder().withOwnerName("").build());
        AvailabilityRepresentation differentStatus =
                new AvailabilityRepresentation(new SampleAvailabilityBuilder().withStatus("").build());
        AvailabilityRepresentation differentStartDate =
                new AvailabilityRepresentation(new SampleAvailabilityBuilder().withStartDateTime(null).build());
        AvailabilityRepresentation differentEndDate =
                new AvailabilityRepresentation(new SampleAvailabilityBuilder().withEndDateTime(null).build());

        Set<User> differentSharedList = new HashSet<User>();
        differentSharedList.add(new User(TestConstants.EMAIL, TestConstants.NAME, FACEBOOK_ID));
        differentSharedList.add(new User("another email", TestConstants.NAME, FACEBOOK_ID));
        AvailabilityRepresentation differentSharedUsers =
                new AvailabilityRepresentation(new SampleAvailabilityBuilder().withSharedUsers(differentSharedList).build());

        assertNotEquals(differentTitle, representation);
        assertNotEquals(differentEmail, representation);
        assertNotEquals(differentName, representation);
        assertNotEquals(differentStatus, representation);
        assertNotEquals(differentSharedUsers, representation);
        assertNotEquals(differentStartDate, representation);
        assertNotEquals(differentEndDate, representation);
        assertNotEquals("", representation);
        assertNotEquals(null, representation);
    }

    @Test
    public void testJsonRepresentation() throws IOException {
        DateTimeFormatter formatter =
                DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");

        String startDateTime = formatter.print(TestConstants.START_DATE_TIME);
        String endDateTime = formatter.print(TestConstants.END_DATE_TIME);

        String expectedJson = "{" + NEWLINE +
                "  \"title\" : \""+ TestConstants.TITLE+"\"," + NEWLINE +
                "  \"ownerEmail\" : \""+ TestConstants.EMAIL+"\"," + NEWLINE +
                "  \"ownerName\" : \""+ TestConstants.NAME+"\"," + NEWLINE +
                "  \"status\" : \""+ TestConstants.STATUS+"\"," + NEWLINE +
                "  \"sharedWithUsers\" : null,"+ NEWLINE +
                "  \"startDateTime\" : \""+startDateTime+"\","+ NEWLINE +
                "  \"endDateTime\" : \""+endDateTime+"\","+ NEWLINE +
                "  \"id\" : 0" + NEWLINE +
                "}";

        AvailabilityRepresentation representation = new AvailabilityRepresentation(availability);
        String actualJson = toJson(representation);
        assertEquals(expectedJson, actualJson);
    }

}

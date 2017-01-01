package representation.availability;

import org.joda.time.DateTime;
import representation.TestConstants;
import representation.user.User;

import java.util.Set;

public class SampleAvailabilityBuilder extends AvailabilityBuilder {
    private int id = TestConstants.ID;
    private String title = TestConstants.TITLE;
    private String ownerEmail = TestConstants.EMAIL;
    private String ownerName = TestConstants.NAME;
    private String status = TestConstants.STATUS;
    private Set<User> sharedWithUsers = null;
    private DateTime startDateTime = TestConstants.START_DATE_TIME;
    private DateTime endDateTime = TestConstants.END_DATE_TIME;

    public SampleAvailabilityBuilder() {
        withId(id);
        withTitle(title);
        withOwnerEmail(ownerEmail);
        withOwnerName(ownerName);
        withStatus(status);
        withSharedUsers(sharedWithUsers);
        withStartDateTime(startDateTime);
        withEndDateTime(endDateTime);
    }
}

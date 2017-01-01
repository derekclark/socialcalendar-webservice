package representation.availability;

import org.joda.time.DateTime;
import representation.user.User;

import java.util.HashSet;
import java.util.Set;

public class SampleAvailabilityBuilder extends AvailabilityBuilder {
    private int id = 0;
    private String title = "title";
    private String ownerEmail = "email";
    private String ownerName = "name";
    private String status = "status";
    private Set<User> sharedWithUsers = new HashSet<>();
    private DateTime startDateTime = new DateTime(2016,1,1,12,0);
    private DateTime endDateTime = new DateTime(2016,1,1,13,0);

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

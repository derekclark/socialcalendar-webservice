package representation.availability;

import org.joda.time.DateTime;
import representation.user.User;

import java.util.Set;

public class SampleAvailabilityBuilder extends AvailabilityBuilder {
    public static final int ID=0;
    public static final String STATUS = "status";
    public static final String NAME = "name";
    public static final String EMAIL = "email";
    public static final String TITLE = "title";
    public static final DateTime START_DATE_TIME = new DateTime(2016,1,1,12,0);
    public static final DateTime END_DATE_TIME = new DateTime(2016,1,1,13,0);

    private int id = ID;
    private String title = TITLE;
    private String ownerEmail = EMAIL;
    private String ownerName = NAME;
    private String status = STATUS;
    private Set<User> sharedWithUsers = null;
    private DateTime startDateTime = START_DATE_TIME;
    private DateTime endDateTime = END_DATE_TIME;

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

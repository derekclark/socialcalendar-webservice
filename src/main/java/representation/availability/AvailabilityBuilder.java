package representation.availability;

import org.joda.time.DateTime;
import representation.user.User;

import java.util.Set;

public class AvailabilityBuilder {
    private int id;
    private String title;
    private String ownerEmail;
    private String ownerName;
    private String status;
    private Set<User> sharedWithUsers;
    private DateTime startDateTime, endDateTime;

    public AvailabilityBuilder withId(int id){
        this.id = id;
        return this;
    }

    public AvailabilityBuilder withTitle(String title){
        this.title = title;
        return this;
    }

    public AvailabilityBuilder withOwnerEmail(String ownerEmail){
        this.ownerEmail = ownerEmail;
        return this;
    }

    public AvailabilityBuilder withOwnerName(String ownerName){
        this.ownerName = ownerName;
        return this;
    }

    public AvailabilityBuilder withStatus(String status){
        this.status = status;
        return this;
    }

    public AvailabilityBuilder withSharedUsers(Set<User> sharedUsers){
        this.sharedWithUsers = sharedUsers;
        return this;
    }

    public AvailabilityBuilder withStartDateTime(DateTime startDateTime){
        this.startDateTime = startDateTime;
        return this;
    }

    public AvailabilityBuilder withEndDateTime(DateTime endDateTime){
        this.endDateTime = endDateTime;
        return this;
    }

    public Availability build(){
        return new Availability(id, title, ownerEmail, ownerName, status, sharedWithUsers, startDateTime, endDateTime);
    }
}

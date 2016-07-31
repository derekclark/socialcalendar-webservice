package representation.availability;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.joda.time.DateTime;
import representation.user.User;
import utilities.CustomDateSerializer;

import java.util.Set;

public class AvailabilityRepresentation {
    @JsonProperty("id")
    private int id;
    @JsonProperty("title")
    private String title;
    @JsonProperty("ownerEmail")
    private String ownerEmail;
    @JsonProperty("ownerName")
    private String ownerName;
    @JsonProperty("status")
    private String status;
    @JsonProperty("sharedWithUsers")
    private Set<User> sharedWithUsers;
    @JsonProperty("startDateTime")
    private DateTime startDateTime;
    @JsonProperty("endDateTime")
    private DateTime endDateTime;

    @JsonCreator
    public AvailabilityRepresentation(@JsonProperty("title")String title,
                                      @JsonProperty("ownerEmail")String ownerEmail,
                                      @JsonProperty("ownerName")String ownerName,
                                      @JsonProperty("status")String status,
                                      @JsonProperty("sharedWithUsers") Set<User> sharedWithUsers,
                                      @JsonProperty("startDateTime") DateTime startDateTime,
                                      @JsonProperty("endDateTime") DateTime endDateTime) {
        this.title = title;
        this.ownerEmail = ownerEmail;
        this.ownerName = ownerName;
        this.status = status;
        this.sharedWithUsers = sharedWithUsers;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }


    public AvailabilityRepresentation(Availability availability){
        this.id = availability.getId();
        this.title = availability.getTitle();
        this.ownerEmail = availability.getOwnerEmail();
        this.ownerName = availability.getOwnerName();
        this.status = availability.getStatus();
        this.sharedWithUsers = availability.getSharedWithUsers();
        this.startDateTime = availability.getStartDateTime();
        this.endDateTime = availability.getEndDateTime();
    }

    public Availability asAvailability() {
        return new Availability(title, ownerEmail, ownerName, status, sharedWithUsers, startDateTime, endDateTime);
    }

    @JsonSerialize(using = CustomDateSerializer.class)
    public DateTime getStartDateTime() {
        return startDateTime;
    }

    @JsonSerialize(using = CustomDateSerializer.class)
    public DateTime getEndDateTime() {
        return endDateTime;
    }

    @Override
    public String toString() {
        return "AvailabilityRepresentation{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", ownerEmail='" + ownerEmail + '\'' +
                ", ownerName='" + ownerName + '\'' +
                ", status='" + status + '\'' +
                ", sharedWithUsers=" + sharedWithUsers +
                ", startDateTime=" + startDateTime +
                ", endDateTime=" + endDateTime +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AvailabilityRepresentation that = (AvailabilityRepresentation) o;

        if (id != that.id) return false;
        if (endDateTime != null ? !endDateTime.equals(that.endDateTime) : that.endDateTime != null) return false;
        if (ownerEmail != null ? !ownerEmail.equals(that.ownerEmail) : that.ownerEmail != null) return false;
        if (ownerName != null ? !ownerName.equals(that.ownerName) : that.ownerName != null) return false;
        if (sharedWithUsers != null ? !sharedWithUsers.equals(that.sharedWithUsers) : that.sharedWithUsers != null)
            return false;
        if (startDateTime != null ? !startDateTime.equals(that.startDateTime) : that.startDateTime != null)
            return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (ownerEmail != null ? ownerEmail.hashCode() : 0);
        result = 31 * result + (ownerName != null ? ownerName.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (sharedWithUsers != null ? sharedWithUsers.hashCode() : 0);
        result = 31 * result + (startDateTime != null ? startDateTime.hashCode() : 0);
        result = 31 * result + (endDateTime != null ? endDateTime.hashCode() : 0);
        return result;
    }
}

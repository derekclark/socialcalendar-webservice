package representation.availability;

import org.joda.time.DateTime;
import representation.user.User;

import java.util.Set;

public class Availability {
    private int id;
    private String title;
    private String ownerEmail;
    private String ownerName;
    private String status;
    Set<User> sharedWithUsers;
    DateTime startDateTime, endDateTime;

    public Availability(String title, String ownerEmail, String ownerName, String status, Set<User> sharedWithUsers) {
        this.title = title;
        this.ownerEmail = ownerEmail;
        this.ownerName = ownerName;
        this.status = status;
        this.sharedWithUsers = sharedWithUsers;
    }

    public Availability() {
    }

    public Availability(int id, String title, String ownerEmail, String ownerName, String status) {
        this.id = id;
        this.title = title;
        this.ownerEmail = ownerEmail;
        this.ownerName = ownerName;
        this.status = status;
    }

    public Availability(String title,
                        String ownerEmail,
                        String ownerName,
                        String status) {
        this.title = title;
        this.ownerEmail = ownerEmail;
        this.ownerName = ownerName;
        this.status = status;
    }

    public Availability(String title, String ownerEmail, String ownerName, String status, Set<User> sharedWithUsers, DateTime startDateTime) {
        this.title = title;
        this.ownerEmail = ownerEmail;
        this.ownerName = ownerName;
        this.status = status;
        this.sharedWithUsers = sharedWithUsers;
        this.startDateTime = startDateTime;
    }

    public Availability(String title, String ownerEmail, String ownerName, String status, Set<User> sharedWithUsers,
                        DateTime startDateTime, DateTime endDateTime) {
        this.title = title;
        this.ownerEmail = ownerEmail;
        this.ownerName = ownerName;
        this.status = status;
        this.sharedWithUsers = sharedWithUsers;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public String getStatus() {
        return status;
    }

    public Set<User> getSharedWithUsers() {
        return sharedWithUsers;
    }

    public DateTime getStartDateTime() {
        return startDateTime;
    }

    public DateTime getEndDateTime() {
        return endDateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Availability that = (Availability) o;

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

    @Override
    public String toString() {
        return "Availability{" +
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
}

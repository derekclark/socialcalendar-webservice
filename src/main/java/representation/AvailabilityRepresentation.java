package representation;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

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

    @JsonCreator
    public AvailabilityRepresentation(@JsonProperty("title")String title,
                                      @JsonProperty("ownerEmail")String ownerEmail,
                                      @JsonProperty("ownerName")String ownerName,
                                      @JsonProperty("status")String status) {
        this.title = title;
        this.ownerEmail = ownerEmail;
        this.ownerName = ownerName;
        this.status = status;
    }

    public AvailabilityRepresentation(Availability availability){
        this.id = availability.getId();
        this.title = availability.getTitle();
        this.ownerEmail = availability.getOwnerEmail();
        this.ownerName = availability.getOwnerName();
        this.status = availability.getStatus();
    }

    public Availability asAvailability() {
        return new Availability(title, ownerEmail, ownerName, status);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AvailabilityRepresentation that = (AvailabilityRepresentation) o;

        if (id != that.id) return false;
        if (ownerEmail != null ? !ownerEmail.equals(that.ownerEmail) : that.ownerEmail != null) return false;
        if (ownerName != null ? !ownerName.equals(that.ownerName) : that.ownerName != null) return false;
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
        return result;
    }

    @Override
    public String toString() {
        return "AvailabilityRepresentation{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", ownerEmail='" + ownerEmail + '\'' +
                ", ownerName='" + ownerName + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}

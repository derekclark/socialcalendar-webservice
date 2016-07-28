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
}

package representation;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
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

    public Availability asAvailability() {
        return new Availability(title, ownerEmail, ownerName, status);
    }
}

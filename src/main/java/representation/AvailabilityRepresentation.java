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
    @JsonProperty("startDate")
    private LocalDateTime startDate;
    @JsonProperty("endDate")
    private LocalDateTime endDate;
    @JsonProperty("ownerEmail")
    private String ownerEmail;
    @JsonProperty("ownerName")
    private String ownerName;
    @JsonProperty("status")
    private String status;
    @JsonProperty("sharedList")
    private Set<User> sharedList;

    @JsonCreator
    public AvailabilityRepresentation(@JsonProperty("title")String title,
                                      @JsonProperty("startDate")LocalDateTime startDate,
                                      @JsonProperty("endDate")LocalDateTime endDate,
                                      @JsonProperty("ownerEmail")String ownerEmail,
                                      @JsonProperty("ownerName")String ownerName,
                                      @JsonProperty("status")String status,
                                      @JsonProperty("sharedList")Set<User> sharedList) {
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.ownerEmail = ownerEmail;
        this.ownerName = ownerName;
        this.status = status;
        this.sharedList = sharedList;
    }

    public Availability asAvailability() {
        return new Availability(title, startDate, endDate, ownerEmail, ownerName, status, sharedList);
    }
}

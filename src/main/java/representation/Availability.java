package representation;

import org.joda.time.LocalDateTime;

import java.util.Set;

public class Availability {
    private int id;
    private String title;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    private String ownerEmail;
    private String ownerName;
    private String status;
    private Set<User> sharedList;

    public Availability(String title, LocalDateTime startDate, LocalDateTime endDate, String ownerEmail,
                        String ownerName, String status, Set<User> sharedList) {
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.ownerEmail = ownerEmail;
        this.ownerName = ownerName;
        this.status = status;
        this.sharedList = sharedList;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
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

    public Set<User> getSharedList() {
        return sharedList;
    }
}

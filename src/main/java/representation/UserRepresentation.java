package representation;

public class UserRepresentation {
    private String email, name, facebookId;

    public UserRepresentation(String email, String name, String facebookId) {
        this.email = email;
        this.name = name;
        this.facebookId = facebookId;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getFacebookId() {
        return facebookId;
    }
}

package representation;

public class User {
    private String email, name, facebookId;

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getFacebookId() {
        return facebookId;
    }

    public User(String email, String name, String facebookId) {
        this.email = email;
        this.name = name;
        this.facebookId = facebookId;


    }
}

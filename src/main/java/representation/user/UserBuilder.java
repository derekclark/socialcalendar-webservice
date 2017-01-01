package representation.user;

public class UserBuilder {
    private String email, name, facebookId;

    public UserBuilder withEmail(String email){
        this.email = email;
        return this;
    }

    public UserBuilder withName(String name){
        this.name = name;
        return this;
    }

    public UserBuilder withFacebookId(String facebookId){
        this.facebookId = facebookId;
        return this;
    }

    public User build() {
        return new User(email, name, facebookId);
    }
}

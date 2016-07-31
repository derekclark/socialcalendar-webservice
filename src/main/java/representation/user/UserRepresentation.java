package representation.user;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserRepresentation {
    @JsonProperty("email")
    String email;
    @JsonProperty("name")
    String name;
    @JsonProperty("facebookId")
    String facebookId;

    public UserRepresentation(@JsonProperty("email") String email,
                              @JsonProperty("name") String name,
                              @JsonProperty("facebookId") String facebookId) {
        this.email = email;
        this.name = name;
        this.facebookId = facebookId;
    }

    public UserRepresentation(User user){
        this.email = user.getEmail();
        this.name = user.getName();
        this.facebookId = user.getFacebookId();
    }

    public User asUser(){
        return new User(email, name, facebookId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserRepresentation that = (UserRepresentation) o;

        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (facebookId != null ? !facebookId.equals(that.facebookId) : that.facebookId != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = email != null ? email.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (facebookId != null ? facebookId.hashCode() : 0);
        return result;
    }
}

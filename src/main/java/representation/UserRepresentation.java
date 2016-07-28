package representation;

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
}

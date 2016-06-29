package representation;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;

import java.io.IOException;

public class UserRepresentation {
    @JsonProperty("User")
    private User user;

    public UserRepresentation(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public String toJson () throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationConfig.Feature.INDENT_OUTPUT);
        return objectMapper.writeValueAsString(this);
    }

}

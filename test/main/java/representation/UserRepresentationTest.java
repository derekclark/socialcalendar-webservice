package representation;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class UserRepresentationTest {
    public static final String EMAIL = "email";
    public static final String NAME = "name";
    public static final String FACEBOOK_ID = "facebookId";

    User user = new User(EMAIL, NAME, FACEBOOK_ID);
    UserRepresentation representation;

    @Before
    public void setup(){
        representation = new UserRepresentation(user);
    }

    @Test
    public void canCreateRepresentation(){
        assertEquals(user,representation.getUser());
    }

    @Test
    public void canMarshallToJson() throws IOException {
        String expectedJson = "{\n" +
                "  \"user\" : {\n" +
                "    \"email\" : \"email\",\n" +
                "    \"name\" : \"name\",\n" +
                "    \"facebookId\" : \"facebookId\"\n" +
                "  }\n" +
                "}";

        assertEquals(expectedJson, getJsonString(representation));
    }

    private String getJsonString (Object object) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationConfig.Feature.INDENT_OUTPUT);
        return objectMapper.writeValueAsString(object);
    }

}

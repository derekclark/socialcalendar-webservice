package utilities;

import org.junit.Test;
import representation.user.User;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class JsonUtilityTest {

        public static final String EMAIL = "email";
        public static final String NAME = "name";
        public static final String FACEBOOK_ID = "facebookId";
        public static final String JSON_STRING= "{\n" +
                "  \"email\" : \"" + EMAIL + "\",\n" +
                "  \"name\" : \""+NAME+"\",\n" +
                "  \"facebookId\" : \""+FACEBOOK_ID+"\"\n" +
                "}";

        User user = new User(EMAIL, NAME, FACEBOOK_ID);
        JsonUtility jsonUtility = new JsonUtility();

        @Test
        public void canMarshallToJson() throws IOException {
            String expectedJson = JSON_STRING;
            assertEquals(expectedJson, jsonUtility.toJson(user));
        }

        @Test
        public void canUnMarshallToObject() throws IOException {
            String jsonString = JSON_STRING;
            User expectedUser = new User(EMAIL, NAME, FACEBOOK_ID);
            User actualUser = jsonUtility.unMarshallJson(jsonString, User.class);
            assertEquals(expectedUser, actualUser);
        }

}

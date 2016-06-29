package utilities;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;

import java.io.IOException;

public class JsonUtility {
    public String toJson (Object object) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationConfig.Feature.INDENT_OUTPUT);
        return objectMapper.writeValueAsString(object);
    }

    public <T> T unMarshallJson(String jsonString, Class<T> clazz) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(jsonString, clazz);
    }

}

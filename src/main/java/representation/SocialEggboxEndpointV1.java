package representation;

import utilities.JsonUtility;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;

@Path("/social/v1/")
@Produces(MediaType.APPLICATION_JSON)
public class SocialEggboxEndpointV1 {
    public static final int HTTP_STATUS_OK = 200;
    public static final int HTTP_STATUS_NOT_FOUND = 404;
    public static final int HTTP_STATUS_BAD_REQUEST = 400;
    UserDAO userRepository;

    public SocialEggboxEndpointV1(UserDAO userRepository) {
        this.userRepository = userRepository;
    }

    @Path("/user/{id}")
    @GET
    public Response getUserById(String userId) {
        User user = userRepository.read(userId);
        if (user != null){
            return okOnRead(user);
        }
        else {
            return notFoundStatus();
        }
    }

    private Response okOnRead(User user){
        try {
            return Response.status(HTTP_STATUS_OK).entity(marshall(user)).build();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String marshall(User user) throws IOException {
        return new JsonUtility().toJson(user);
    }

    private Response notFoundStatus(){
        return Response.status(HTTP_STATUS_NOT_FOUND).entity(null).build();
    }

    public Response saveUser(String userPayload) throws IOException {
        if (userPayload.isEmpty())
            return badRequestOnSave();
        else {
            return okSave(userPayload);
        }
    }

    private Response okSave(String userPayload) throws IOException {
        User user = unmarshall(userPayload);
        userRepository.save(user);
        return Response.status(HTTP_STATUS_OK).entity(userPayload).build();
    }

    private Response badRequestOnSave() {
        return Response.status(HTTP_STATUS_BAD_REQUEST).build();
    }

    private User unmarshall(String userPayload) throws IOException {
        return new JsonUtility().unMarshallJson(userPayload, User.class);
    }
}

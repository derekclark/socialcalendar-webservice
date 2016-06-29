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
            return okStatus(user);
        }
        else {
            return notFoundStatus();
        }
    }

    private Response okStatus(User user){
        try {
            return Response.status(HTTP_STATUS_OK).entity(new JsonUtility().toJson(user)).build();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Response notFoundStatus(){
        return Response.status(HTTP_STATUS_NOT_FOUND).entity(null).build();
    }

    public Response saveUser(String userPayload) {
        if (userPayload.isEmpty())
            return Response.status(HTTP_STATUS_BAD_REQUEST).build();
        else
            return Response.status(HTTP_STATUS_OK).build();
    }
}

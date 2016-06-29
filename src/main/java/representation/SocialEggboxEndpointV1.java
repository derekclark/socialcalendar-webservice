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
            return Response.status(200).entity(new JsonUtility().toJson(user)).build();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Response notFoundStatus(){
        return Response.status(404).entity(null).build();
    }
}

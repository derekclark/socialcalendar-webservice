package representation;

import utilities.JsonUtility;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.io.IOException;

@Path("/social/v1/")
@Produces("application/json")
@Consumes("application/json")

public class SocialEggboxEndpointV1 {
    public static final int HTTP_STATUS_OK = 200;
    public static final int HTTP_STATUS_NOT_FOUND = 404;
    public static final int HTTP_STATUS_BAD_REQUEST = 400;
    UserDAO userRepository;

    public SocialEggboxEndpointV1(UserDAO userRepository) {
        this.userRepository = userRepository;
    }

    @GET
    @Path("user/{id}")
    public Response getUserById(@PathParam("id") String userId) {
        User user = userRepository.read(userId);
        if (user != null){
            return okOnRead(user);
        }
        else {
            return notFoundStatus();
        }
    }

    @POST
    @Path("user")
    public Response saveUser(String userPayload) throws IOException {
        if (userPayload.isEmpty())
            return badRequestOnSave();
        else {
            return okSave(userPayload);
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

    public Response delete(String email) {
        boolean ok = userRepository.delete(email);
        if (ok){
            return okOnDelete();
        }
        else {
            return notFoundStatus();
        }
    }

    private Response okOnDelete() {
        return Response.status(HTTP_STATUS_OK).build();
    }

}

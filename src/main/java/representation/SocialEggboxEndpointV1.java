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
    AvailabilityDAO availabilityRepository;

    public SocialEggboxEndpointV1(UserDAO userRepository, AvailabilityDAO availabilityRepository) {
        this.userRepository = userRepository;
        this.availabilityRepository = availabilityRepository;
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
    public Response saveUser(UserRepresentation userRepresentation) throws IOException {
        if (userRepresentation == null) {
            return badRequest();
        }
        else {
            return okUserSave(userRepresentation);
        }
    }

    @DELETE
    @Path("user/{id}")
    public Response deleteUser(@PathParam("id") String email) {
        boolean ok = userRepository.delete(email);
        if (ok){
            return okOnDelete();
        }
        else {
            return notFoundStatus();
        }
    }

    @POST
    @Path("availability")
    public Response createAvailability(AvailabilityRepresentation representation) throws IOException {
        if (representation == null)
            return badRequest();
        else
            return okAvailabilitySave(representation);
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

    private Response badRequest() {
        return Response.status(HTTP_STATUS_BAD_REQUEST).build();
    }

    private Response okOnDelete() {
        return Response.status(HTTP_STATUS_OK).build();
    }

    private Response okUserSave(UserRepresentation userRepresentation) throws IOException {
        User user = userRepresentation.asUser();
        userRepository.save(user);
        return Response.status(HTTP_STATUS_OK).entity(user).build();
    }

    private Response okAvailabilitySave(AvailabilityRepresentation representation) throws IOException {
        Availability availability = representation.asAvailability();
        availabilityRepository.save(availability);
        return Response.status(HTTP_STATUS_OK).entity(new AvailabilityRepresentation(availability)).build();
    }
}

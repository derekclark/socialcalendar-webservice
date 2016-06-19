package representation;

import javax.ws.rs.core.Response;

public class SocialEggboxRepresentationV1 {
    public Response getUserById(String userid) {
        Response.ResponseBuilder builder= Response.status(200).entity(new User("email","name","facebookId"));
        return builder.build();
    }
}

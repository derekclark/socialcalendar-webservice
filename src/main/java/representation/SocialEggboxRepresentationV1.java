package representation;

import javax.ws.rs.core.Response;

public class SocialEggboxRepresentationV1 {
    UserDAO userRepository;

    public SocialEggboxRepresentationV1(UserDAO userRepository) {
        this.userRepository = userRepository;
    }

    public Response getUserById(String userId) {
        User user = userRepository.read(userId);
        Response.ResponseBuilder builder;
        if (user != null){
            builder= Response.status(200).entity(new User("email","name","facebookId"));
        }
        else {
            builder= Response.status(404).entity(null);
        }

        return builder.build();
    }
}

package representation;

import javax.ws.rs.core.Response;

public class SocialEggboxRepresentationV1 {
    UserDAO userRepository;

    public SocialEggboxRepresentationV1(UserDAO userRepository) {
        this.userRepository = userRepository;
    }

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
        return Response.status(200).entity(user).build();
    }

    private Response notFoundStatus(){
        return Response.status(404).entity(null).build();
    }
}

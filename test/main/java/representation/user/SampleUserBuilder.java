package representation.user;

public class SampleUserBuilder extends UserBuilder{

    public static final String EMAIL = "email";
    public static final String NAME = "name";
    public static final String FACEBOOKID = "facebookId";

    private String email = EMAIL;
    private String name = NAME;
    private String facebookid = FACEBOOKID;

    public SampleUserBuilder() {
        withEmail(email);
        withName(name);
        withFacebookId(facebookid);

    }
}

package representation.user;

import representation.TestConstants;

public class SampleUserBuilder extends UserBuilder{
    private String email = TestConstants.EMAIL;
    private String name = TestConstants.NAME;
    private String facebookid = TestConstants.FACEBOOKID;

    public SampleUserBuilder() {
        withEmail(email);
        withName(name);
        withFacebookId(facebookid);
    }
}

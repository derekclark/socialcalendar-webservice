package uk.co.socialeggbox.stepdefs;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import representation.User;
import uk.co.tpplc.http.Response;
import uk.co.tpplc.http.SimpleHttpClient;
import utilities.JsonUtility;

import static org.junit.Assert.assertEquals;

public class GetUserById {
    public static final String URL = "http://localhost:9000/social/v1/user/";
    Response response;

    @Given("^user exists with email \"(.*?)\", name \"(.*?)\" and facebookId \"(.*?)\"$")
    public void user_exists_with_email_name_and_facebookId(String email,
                                                           String name, String facebookId) throws Throwable {
        response = new SimpleHttpClient().post(URL,
                new JsonUtility().toJson(new User(email, name, facebookId)));
        assertEquals(response.getStatus(), 200);
    }

    @When("^a request is made to get user with email \"(.*?)\"$")
    public void a_request_is_made_to_get_user_with_email(String email) throws Throwable {
        response = new SimpleHttpClient().get(URL+email);
    }


    @Then("^the payload is returned with email \"(.*?)\", name \"(.*?)\", facebookId \"(.*?)\" and a http status of (\\d+)$")
    public void the_payload_is_returned_with_email_name_facebookId_and_a_http_status_of(String expectedEmail,
                         String expectedName, String expectedFacebookId, int expectedHttpStatus) throws Throwable {
        String expectedJson = "{\n" +
                "  \"email\" : \"decla\",\n" +
                "  \"name\" : \"derek\",\n" +
                "  \"facebookId\" : \"1234\"\n" +
                "}";
        assertEquals(expectedJson,response.getBody());
        assertEquals(response.getStatus(), expectedHttpStatus);
    }
}

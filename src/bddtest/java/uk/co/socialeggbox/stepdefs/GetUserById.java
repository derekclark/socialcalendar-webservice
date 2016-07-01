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

//    @After
//    public void teardown(){
//        response = new SimpleHttpClient().delete(URL+"decla");
//        assertEquals(200, response.getStatus());
//    }

    @Given("^user exists with email \"(.*?)\", name \"(.*?)\" and facebookId \"(.*?)\"$")
    public void user_exists_with_email_name_and_facebookId(String email,
                                                           String name, String facebookId) throws Throwable {
        System.out.println("writing user with id="+email);
        response = new SimpleHttpClient().post(URL,
                new JsonUtility().toJson(new User(email, name, facebookId)));
        assertEquals(200, response.getStatus());
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

    @When("^a request is made to delete user with email \"(.*?)\"$")
    public void a_request_is_made_to_get_delete_user_with_email(String email) throws Throwable {
        String url = URL + email;
        System.out.println("delete url="+url);
        response = new SimpleHttpClient().delete(url);
    }

    @Then("^a http code status of (\\d+) is returned$")
    public void a_http_code_status_of_is_returned(int status) throws Throwable {
        assertEquals(status, response.getStatus());
    }

    @Then("^the user with email \"(.*?)\" does not exist$")
    public void the_user_with_email_does_not_exist(String email) throws Throwable {
        response = new SimpleHttpClient().get(URL+email);
        assertEquals(404, response.getStatus());
    }

}

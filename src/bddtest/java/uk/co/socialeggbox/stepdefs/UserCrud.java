package uk.co.socialeggbox.stepdefs;

import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import representation.user.User;
import uk.co.tpplc.http.Response;
import uk.co.tpplc.http.SimpleHttpClient;
import utilities.JsonUtility;

import static org.junit.Assert.assertEquals;

public class UserCrud {
    public static final String URL = "http://localhost:9000/social/v1/user/";
    public static final int HTTP_STATUS_OK = 200;
    public static final int HTTP_STATUS_NOT_FOUND = 404;
    Response response;

    @After
    public void teardown(){
        response = new SimpleHttpClient().delete(URL+"decla");

        if (response.getStatus() == HTTP_STATUS_OK){
            response = new SimpleHttpClient().delete(URL+"decla");
        }
    }

    @Given("^user exists with email \"(.*?)\", name \"(.*?)\" and facebookId \"(.*?)\"$")
    public void user_exists_with_email_name_and_facebookId(String email,
                                                           String name, String facebookId) throws Throwable {
        response = new SimpleHttpClient().post(URL,
                new JsonUtility().toJson(new User(email, name, facebookId)));
        assertEquals(HTTP_STATUS_OK, response.getStatus());
    }

    @When("^a request is made to get user with email \"(.*?)\"$")
    public void a_request_is_made_to_get_user_with_email(String email) throws Throwable {
        response = new SimpleHttpClient().get(URL+email);
    }

    @Then("^the payload is returned with email \"(.*?)\", name \"(.*?)\", facebookId \"(.*?)\"$")
    public void the_payload_is_returned_with_email_name_facebookId(String email, String name, String facebookId) throws Throwable {
        String expectedJson = "{\n" +
                "  \"email\" : \""+email+"\",\n" +
                "  \"name\" : \""+name+"\",\n" +
                "  \"facebookId\" : \""+facebookId+"\"\n" +
                "}";
        assertEquals(expectedJson,response.getBody());
    }

    @When("^a request is made to delete user with email \"(.*?)\"$")
    public void a_request_is_made_to_get_delete_user_with_email(String email) throws Throwable {
        response = new SimpleHttpClient().delete(URL + email);
    }

    @Then("^a http code status of (\\d+) is returned$")
    public void a_http_code_status_of_is_returned(int status) throws Throwable {
        assertEquals(status, response.getStatus());
    }

    @Then("^the user with email \"(.*?)\" does not exist$")
    public void the_user_with_email_does_not_exist(String email) throws Throwable {
        response = new SimpleHttpClient().get(URL+email);
        assertEquals(HTTP_STATUS_NOT_FOUND, response.getStatus());
    }

    @Given("^a request is made to save user with email \"(.*?)\", name \"(.*?)\" and facebookId \"(.*?)\"$")
    public void a_request_is_made_to_save_user_with_email_name_and_facebookId(String email,
                                                                              String name,
                                                                              String facebookId) throws Throwable {
        String payload = "{\n" +
                "  \"email\" : \""+email+"\",\n" +
                "  \"name\" : \""+name+"\",\n" +
                "  \"facebookId\" : \""+facebookId+"\"\n" +
                "}";

        response = new SimpleHttpClient().post(URL, payload);
    }
}

package uk.co.socialeggbox.stepdefs;

import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.http.HttpResponse;
import representation.user.User;
import utilities.JsonUtility;

import static org.junit.Assert.assertEquals;
import static uk.co.socialeggbox.stepdefs.HttpCall.*;

public class UserCrud {
    public static final String URL = "http://localhost:9000/social/v1/user/";
    public static final int HTTP_STATUS_OK = 200;
    public static final int HTTP_STATUS_NOT_FOUND = 404;
//    Response response;
    HttpResponse httpResponse;
    String responseBody;

    @After
    public void teardown(){
        httpResponse = new HttpCall().delete(URL + "decla");
//        response = new SimpleHttpClient().delete(URL+"decla");

        if (getResponseCode(httpResponse) == HTTP_STATUS_OK){
            httpResponse = delete(URL + "decla");
        }
    }

    @Given("^user exists with email \"(.*?)\", name \"(.*?)\" and facebookId \"(.*?)\"$")
    public void user_exists_with_email_name_and_facebookId(String email,
                                                           String name, String facebookId) throws Throwable {
        httpResponse = post(URL, new JsonUtility().toJson(new User(email, name, facebookId)));
        assertEquals(HTTP_STATUS_OK, getResponseCode(httpResponse));
    }

    @When("^a request is made to get user with email \"(.*?)\"$")
    public void a_request_is_made_to_get_user_with_email(String email) throws Throwable {
        httpResponse = get(URL + email);
    }

    @Then("^the payload is returned with email \"(.*?)\", name \"(.*?)\", facebookId \"(.*?)\"$")
    public void the_payload_is_returned_with_email_name_facebookId(String email, String name, String facebookId) throws Throwable {
        String expectedJson = "{\n" +
                "  \"email\" : \""+email+"\",\n" +
                "  \"name\" : \""+name+"\",\n" +
                "  \"facebookId\" : \""+facebookId+"\"\n" +
                "}";
        assertEquals(expectedJson, getResponseBody(httpResponse));
    }

    @When("^a request is made to delete user with email \"(.*?)\"$")
    public void a_request_is_made_to_get_delete_user_with_email(String email) throws Throwable {
        httpResponse = delete(URL + email);
    }

    @Then("^a http code status of (\\d+) is returned$")
    public void a_http_code_status_of_is_returned(int status) throws Throwable {
        assertEquals(status, getResponseCode(httpResponse));
    }

    @Then("^the user with email \"(.*?)\" does not exist$")
    public void the_user_with_email_does_not_exist(String email) throws Throwable {
        httpResponse = get(URL + email);
        assertEquals(HTTP_STATUS_NOT_FOUND, getResponseCode(httpResponse));
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

        httpResponse = post(URL, payload);
    }
}

package uk.co.socialeggbox.stepdefs;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import representation.Availability;
import uk.co.tpplc.http.Response;
import uk.co.tpplc.http.SimpleHttpClient;
import utilities.JsonUtility;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AvailabilityCrud{
    public static final String STATUS = "status";
    public static final String NAME = "Derek";
    public static final String URL = "http://localhost:9000/social/v1/availability/";
    public static final int HTTP_STATUS_OK = 200;
    public static final String PAYLOAD = "{" +
            "\"id\" : 0," +
            "\"title\" : \"new event\"," +
            "\"ownerEmail\" : \"decla\"," +
            "\"ownerName\" : \"Derek\"," +
            "\"status\" : \"status\"" +
            "}";
    Availability availability;
    Response response;

    @Given("^a new availability with title \"(.*?)\" owned by user id \"(.*?)\"$")
    public void a_new_availability_with_title_owned_by_user_id(String title, String userId) throws Throwable {
        availability = new Availability(title, userId, NAME, STATUS);
    }

    @When("^a request is made to create the availability$")
    public void a_request_is_made_to_save_the_availability() throws Throwable {
        response = new SimpleHttpClient().post(URL, PAYLOAD);
    }

    @Then("^http code status should be (\\d+)$")
    public void http_code_status_should_be(int arg1) throws Throwable {
        assertEquals(HTTP_STATUS_OK,response.getStatus());
    }

    @Then("^the payload is returned with an id greater than (\\d+)$")
    public void the_payload_is_returned_with_an_id_greater_than(int arg1) throws Throwable {
        System.out.println("response body="+response.getBody().toString());
        Availability availability = new JsonUtility().unMarshallJson(response.getBody(), Availability.class);

        assertTrue(availability.getId() > 0);
    }
}

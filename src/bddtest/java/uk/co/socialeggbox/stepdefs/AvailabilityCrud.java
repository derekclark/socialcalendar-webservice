package uk.co.socialeggbox.stepdefs;


import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.http.HttpResponse;
import representation.availability.AvailabilityRepresentation;

import static org.junit.Assert.assertEquals;
import static uk.co.socialeggbox.stepdefs.HttpCall.*;
import static utilities.JsonUtility.unMarshallJson;

public class AvailabilityCrud{
    public static final String STATUS = "status";
    public static final String NAME = "Derek";
    public static final String URL = "http://localhost:9000/social/v1/availability/";
    public static final int HTTP_STATUS_OK = 200;
    public static final String TITLE = "new event";
    public static final String EMAIL = "decla";
    public static final String START_DATE_TIME = "2016-01-01T12:30:00.000";
    public static final String END_DATE_TIME = "2016-01-01T13:45:00.000";
    public static final String PAYLOAD = "{" +
            "\"title\" : \"" + TITLE + "\"," +
            "\"ownerEmail\" : \"" + EMAIL + "\"," +
            "\"ownerName\" : \"" + NAME + "\"," +
            "\"status\" : \""+STATUS+"\"," +
            "\"sharedWithUsers\" : [ ],"+
            "  \"startDateTime\" : \"" + START_DATE_TIME + "\"," +
            "  \"endDateTime\" : \"" + END_DATE_TIME + "\"" +
            "}";


    HttpResponse httpResponse;
    String responseBody;
    int id;

    @Given("^a request is made to create an availability$")
    public void a_request_is_made_to_create_an_availability() throws Throwable {
        postAvailability();
    }

    private void postAvailability() throws java.io.IOException {
        httpResponse = post(URL, PAYLOAD);
        responseBody = new HttpCall().getResponseBody(httpResponse);
        AvailabilityRepresentation representation =
                unMarshallJson(responseBody,
                        AvailabilityRepresentation.class);
        id = representation.getId();
    }

    @Then("^http code status should be (\\d+)$")
    public void http_code_status_should_be(int expectedStatus) throws Throwable {
        assertEquals(expectedStatus, getResponseCode(httpResponse));
    }


    @Then("^the payload is returned for the availability$")
    public void the_payload_is_returned_for_the_availability() throws Throwable {
        String expectedPayload = "{\n" +
                "  \"title\" : \"" + TITLE + "\",\n" +
                "  \"ownerEmail\" : \"" + EMAIL + "\",\n" +
                "  \"ownerName\" : \"" + NAME + "\",\n" +
                "  \"status\" : \""+STATUS+"\",\n" +
                "  \"sharedWithUsers\" : null,\n"+
                "  \"startDateTime\" : \"" + START_DATE_TIME + "\",\n" +
                "  \"endDateTime\" : \"" + END_DATE_TIME + "\",\n" +
                "  \"id\" : "+id+ "\n" +
                "}";

        assertEquals(expectedPayload, responseBody);
    }

    @Given("^an availability exists$")
    public void an_availability_exists() throws Throwable {
        postAvailability();
    }

    @When("^a request is made to get that availability$")
    public void a_request_is_made_to_get_that_availability() throws Throwable {
        httpResponse = get(URL + id);
    }





}

package uk.co.socialeggbox.stepdefs;


import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import representation.availability.AvailabilityRepresentation;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static uk.co.socialeggbox.stepdefs.HttpCall.get;
import static uk.co.socialeggbox.stepdefs.HttpCall.post;
import static utilities.JsonUtility.unMarshallJson;

public class AvailabilityCrud{
    public static final String STATUS = "status";
    public static final String NAME = "Derek";
    public static final String BASE_URL = "http://localhost:9000/social/v1/";
    public static final int HTTP_STATUS_OK = 200;
    public static final String TITLE = "new event";
    public static final String EMAIL = "decla_";
    public static final String START_DATE_TIME = "2016-01-01T12:30:00.000";
    public static final String END_DATE_TIME = "2016-01-01T13:45:00.000";
    List<Integer> idList = new ArrayList<>();
    public UUID uid;
    public String uniqueUserId;
    MyHttpResponse httpResponse;
    int id;

    @Before
    public void setup(){
        uid = UUID.randomUUID();
        uniqueUserId = EMAIL+"_"+uid;
    }

    @Given("^a request is made to create an availability$")
    public void a_request_is_made_to_create_an_availability() throws Throwable {
        postAvailability();
    }

    private void postAvailability() throws java.io.IOException {
        String PAYLOAD = "{" +
                "\"title\" : \"" + TITLE + "\"," +
                "\"ownerEmail\" : \"" + uniqueUserId + "\"," +
                "\"ownerName\" : \"" + NAME + "\"," +
                "\"status\" : \""+STATUS+"\"," +
                "\"sharedWithUsers\" : [ ],"+
                "  \"startDateTime\" : \"" + START_DATE_TIME + "\"," +
                "  \"endDateTime\" : \"" + END_DATE_TIME + "\"" +
                "}";

        httpResponse = post(BASE_URL + "availability", PAYLOAD);
        AvailabilityRepresentation representation =
                unMarshallJson(httpResponse.getBody(),
                        AvailabilityRepresentation.class);
        id = representation.getId();
    }

    private int postAvailability(String payload) throws java.io.IOException {
        httpResponse = post(BASE_URL + "availability", payload);
        AvailabilityRepresentation representation =
                unMarshallJson(httpResponse.getBody(),
                        AvailabilityRepresentation.class);
        id = representation.getId();
        return id;
    }

    @Then("^http code status should be (\\d+)$")
    public void http_code_status_should_be(int expectedStatus) throws Throwable {
        assertEquals(expectedStatus, httpResponse.getStatus());
    }

    @Then("^the payload is returned for the availability$")
    public void the_payload_is_returned_for_the_availability() throws Throwable {
        String expectedPayload = "{\n" +
                "  \"title\" : \"" + TITLE + "\",\n" +
                "  \"ownerEmail\" : \"" + uniqueUserId + "\",\n" +
                "  \"ownerName\" : \"" + NAME + "\",\n" +
                "  \"status\" : \""+STATUS+"\",\n" +
                "  \"sharedWithUsers\" : null,\n"+
                "  \"startDateTime\" : \"" + START_DATE_TIME + "\",\n" +
                "  \"endDateTime\" : \"" + END_DATE_TIME + "\",\n" +
                "  \"id\" : "+id+ "\n" +
                "}";

        assertEquals(expectedPayload, httpResponse.getBody());
    }

    @Given("^an availability exists$")
    public void an_availability_exists() throws Throwable {
        postAvailability();
    }

    @When("^a request is made to get that availability$")
    public void a_request_is_made_to_get_that_availability() throws Throwable {
        httpResponse = get(BASE_URL + "availability/" + id);
    }

    @Given("^multiple availabilities exist for a user$")
    public void an_availabilities_exist_for_a_user() throws Throwable {
        int id = 0;

        String payload = "{" +
                "\"title\" : \"" + TITLE + "\"," +
                "\"ownerEmail\" : \"" + uniqueUserId + "\"," +
                "\"ownerName\" : \"" + NAME + "\"," +
                "\"status\" : \""+STATUS+"\"," +
                "\"sharedWithUsers\" : [ ],"+
                "  \"startDateTime\" : \"" + START_DATE_TIME + "\"," +
                "  \"endDateTime\" : \"" + END_DATE_TIME + "\"" +
                "}";

        id = postAvailability(payload);
        idList.add(id);

        payload = "{" +
                "\"title\" : \"" + TITLE + "\"," +
                "\"ownerEmail\" : \"" + "someone else" + "\"," +
                "\"ownerName\" : \"" + NAME + "\"," +
                "\"status\" : \""+STATUS+"\"," +
                "\"sharedWithUsers\" : [ ],"+
                "  \"startDateTime\" : \"" + START_DATE_TIME + "\"," +
                "  \"endDateTime\" : \"" + END_DATE_TIME + "\"" +
                "}";
        postAvailability(payload);

        payload = "{" +
                "\"title\" : \"" + TITLE + "\"," +
                "\"ownerEmail\" : \"" + uniqueUserId + "\"," +
                "\"ownerName\" : \"" + NAME + "\"," +
                "\"status\" : \""+STATUS+"\"," +
                "\"sharedWithUsers\" : [ ],"+
                "  \"startDateTime\" : \"" + START_DATE_TIME + "\"," +
                "  \"endDateTime\" : \"" + END_DATE_TIME + "\"" +
                "}";
        id = postAvailability(payload);
        idList.add(id);
    }

    @When("^a request is made to get all that user's availabilities$")
    public void a_request_is_made_to_get_all_that_user_s_availabilities() throws Throwable {
        httpResponse = get(BASE_URL + "users/" + uniqueUserId + "/availabilities");
    }

    @Then("^the payload contains all the availabilities for that user$")
    public void the_payload_contains_all_the_availabilities_for_that_user() throws Throwable {
        String expectedPayload = "[ {\n" +
                "  \"title\" : \"" + TITLE + "\",\n" +
                "  \"ownerEmail\" : \"" + uniqueUserId + "\",\n" +
                "  \"ownerName\" : \"" + NAME + "\",\n" +
                "  \"status\" : \""+STATUS+"\",\n" +
                "  \"sharedWithUsers\" : null,\n"+
                "  \"startDateTime\" : \"" + START_DATE_TIME + "\",\n" +
                "  \"endDateTime\" : \"" + END_DATE_TIME + "\",\n" +
                "  \"id\" : "+idList.get(0)+ "\n" +
                "}, {\n" +
                "  \"title\" : \"" + TITLE + "\",\n" +
                "  \"ownerEmail\" : \"" + uniqueUserId + "\",\n" +
                "  \"ownerName\" : \"" + NAME + "\",\n" +
                "  \"status\" : \""+STATUS+"\",\n" +
                "  \"sharedWithUsers\" : null,\n"+
                "  \"startDateTime\" : \"" + START_DATE_TIME + "\",\n" +
                "  \"endDateTime\" : \"" + END_DATE_TIME + "\",\n" +
                "  \"id\" : "+idList.get(1)+ "\n" +
                "} ]";

        System.out.println("expected..." + expectedPayload);
        System.out.printf("actual..." + httpResponse.getBody());

        assertEquals(expectedPayload, httpResponse.getBody());

    }
}

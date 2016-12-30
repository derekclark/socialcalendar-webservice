package uk.co.socialeggbox.stepdefs;

public class MyHttpResponse {
    private int status;
    private String body;

    public int getStatus() {
        return status;
    }

    public String getBody() {
        return body;
    }

    public MyHttpResponse(int status, String body) {
        this.status = status;
        this.body = body;
    }
}

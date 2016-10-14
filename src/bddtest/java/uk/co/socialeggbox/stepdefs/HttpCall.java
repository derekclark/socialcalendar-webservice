package uk.co.socialeggbox.stepdefs;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class HttpCall {
    static public HttpResponse post(String url, String payload){
        HttpPost httpPost = new HttpPost(url);
        StringEntity entity = null;
        try {
            entity = new StringEntity(payload);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        httpPost.setEntity(entity);
        httpPost.setHeader("Content-Type","application/json");
        return makeCall(httpPost);
    }

    private static HttpResponse makeCall(HttpRequestBase httpRequest){
        try (CloseableHttpClient httpClient = HttpClients.custom().build()){
            try (CloseableHttpResponse httpResponse = httpClient.execute(httpRequest)) {
                return httpResponse;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    static public HttpResponse get(String url){
        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader("Content-Type","application/json");
        return makeCall(httpGet);
    }

    static public HttpResponse delete(String url){
        HttpDelete httpDelete = new HttpDelete(url);
        httpDelete.setHeader("Content-Type","application/json");
        return makeCall(httpDelete);
    }

    static public int getResponseCode(HttpResponse httpResponse) {
        return httpResponse.getStatusLine().getStatusCode();
    }

    static public String getResponseBody(HttpResponse httpResponse){
        try {
            return EntityUtils.toString(httpResponse.getEntity());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

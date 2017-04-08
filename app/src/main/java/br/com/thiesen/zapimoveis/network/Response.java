package br.com.thiesen.zapimoveis.network;

/**
 * Created by eduardothiesen on 10/03/17.
 */

public class Response {

    private int statusCode;
    private String jsonResponse;

    public Response() {}

    public Response(int statusCode, String jsonResponse) {
        this.statusCode = statusCode;
        this.jsonResponse = jsonResponse;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getJsonResponse() {
        return jsonResponse;
    }

    public void setJsonResponse(String jsonResponse) {
        this.jsonResponse = jsonResponse;
    }
}

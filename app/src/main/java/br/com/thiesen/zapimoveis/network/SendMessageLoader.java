package br.com.thiesen.zapimoveis.network;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by eduardothiesen on 08/04/17.
 */

class SendMessageLoader extends AsyncTaskLoader<Response> {

    private final static String LOG_TAG = SendMessageLoader.class.getName();

    private String url;
    private String name;
    private String email;
    private String phone;
    private int id;

    public SendMessageLoader(Context context, String url, String name, String email, String phone, int id) {
        super(context);
        this.url = url;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.id = id;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public Response loadInBackground() {
        return fetchData();
    }

    public Response fetchData() {
        URL url = QueryUtils.createUrl(this.url);

        Response response = null;
        try {
            response = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "IO Exception Error", e);
        }

        return response;
    }

    public Response makeHttpRequest(URL url) throws IOException {
        Response response = new Response();

        if (url == null) {
            return response;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;

        String urlParameters = "Nome=" + name + "&Email=" + email + "&Telefone=" + phone + "&CodImovel=" + id;

        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.setRequestMethod("POST");

            DataOutputStream dStream = new DataOutputStream(urlConnection.getOutputStream());
            dStream.writeBytes(urlParameters);
            dStream.flush();
            dStream.close();

            urlConnection.connect();

            response.setStatusCode(urlConnection.getResponseCode());

            if (response.getStatusCode() == 200) {
                inputStream = urlConnection.getInputStream();
                response.setJsonResponse(QueryUtils.readFromStream(inputStream));
            } else {
                inputStream = urlConnection.getErrorStream();
                response.setJsonResponse(QueryUtils.readFromStream(inputStream));
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem sending message.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }

            if (inputStream != null) {
                inputStream.close();
            }
        }

        return response;
    }
}

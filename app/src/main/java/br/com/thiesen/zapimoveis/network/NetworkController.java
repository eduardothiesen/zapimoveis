package br.com.thiesen.zapimoveis.network;

import android.content.Context;
import android.support.v4.content.Loader;

/**
 * Created by eduardothiesen on 10/03/17.
 */

public class NetworkController {

    private Context context;
    protected static final String BASE_URL = "http://demo4573903.mockable.io";

    public NetworkController(Context context) {
        this.context = context;
    }

    public ListRealtiesLoader retrieveRealties() {
        return new ListRealtiesLoader(context, BASE_URL + "/imoveis");
    }

    public Loader<Response> retrieveRealtyDetails(int id) {
        return new RealtyDetailsLoader(context, BASE_URL + "/imoveis/" + id);
    }

    public Loader<Response> sendMessage(String name, String email, String phone, int id) {
        return new SendMessageLoader(context, BASE_URL + "/imoveis/contato", name, email, phone, id);
    }
}

package br.com.thiesen.zapimoveis.network;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.thiesen.zapimoveis.data.Address;
import br.com.thiesen.zapimoveis.data.Client;
import br.com.thiesen.zapimoveis.data.Realty;

/**
 * Created by eduardothiesen on 06/04/17.
 */

public class ResponseParser {

    private static final String LOG_TAG = ResponseParser.class.getName();

    public static List<Realty> parseRealties(String response) {
        List<Realty> realties = new ArrayList<>();

        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray;
            if (jsonObject != null) {
                jsonArray = jsonObject.optJSONArray("Imoveis");

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject parent = jsonArray.getJSONObject(i);
                    JSONObject addressObj = parent.optJSONObject("Endereco");

                    Address address = null;
                    if (addressObj != null) {
                        address = new Address(addressObj.optString("Logradouro"), addressObj.optString("Numero"), addressObj.optString("Complemento"),
                                addressObj.optString("CEP"), addressObj.optString("Bairro"), addressObj.optString("Cidade"), addressObj.optString("Estado"), addressObj.optString("Zona"));
                    }

                    JSONObject clientObj = parent.optJSONObject("Cliente");

                    Client client = null;
                    if (clientObj != null) {
                        client = new Client(clientObj.optInt("CodCliente"), clientObj.optString("NomeFantasia"));
                    }

                    String dateString = parent.optString("DataAtualizacao");

                    Date date = null;
                    if (dateString != null && !dateString.isEmpty() && dateString != "null") {
                        String time = "-" + dateString.split("-")[1];
                        date = new Date(Long.parseLong(time));
                    }

                    Realty realty = new Realty(parent.optInt("CodImovel"), parent.optString("TipoImovel"), address, parent.optDouble("PrecoVenda"), parent.optInt("Dormitorios"),
                            parent.optInt("Suites"), parent.optInt("Vagas"), parent.optDouble("AreaUtil"), parent.optDouble("AreaTotal"), date, client, parent.optString("UrlImagem"),
                            parent.optString("SubTipoOferta"), parent.optString("SubtipoImovel"));

                    realties.add(realty);
                }
            }

        } catch (JSONException e) {
            Log.e(LOG_TAG, "Error parsing Json.", e);
        } finally {
            return realties;
        }
    }

    public static Realty parseRealty(String response) {
        Realty realty = null;
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONObject parent;
            if (jsonObject != null) {
                parent = jsonObject.optJSONObject("Imovel");

                JSONObject addressObj = parent.optJSONObject("Endereco");

                Address address = null;
                if (addressObj != null) {
                    address = new Address(addressObj.optString("Logradouro"), addressObj.optString("Numero"), addressObj.optString("Complemento"),
                            addressObj.optString("CEP"), addressObj.optString("Bairro"), addressObj.optString("Cidade"), addressObj.optString("Estado"), addressObj.optString("Zona"));
                }

                JSONObject clientObj = parent.optJSONObject("Cliente");

                Client client = null;
                if (clientObj != null) {
                    client = new Client(clientObj.optInt("CodCliente"), clientObj.optString("NomeFantasia"));
                }

                String dateString = parent.optString("DataAtualizacao");

                Date date = null;
                if (dateString != null && !dateString.isEmpty() && dateString != "null") {
                    String time = dateString.split("Date")[1];
                    time = time.replace("(", "");
                    time = time.replace(")/", "");
                    date = new Date(Long.parseLong(time));
                }

                JSONArray picturesJSONArray = parent.optJSONArray("Fotos");
                ArrayList<String> pictures = new ArrayList<>();
                for (int i = 0; i < picturesJSONArray.length(); i++) {
                    pictures.add(picturesJSONArray.getString(i));
                }

                JSONArray characteristicsJSONArray = parent.optJSONArray("Caracteristicas");
                ArrayList<String> characteristics = new ArrayList<>();
                for (int i = 0; i < characteristicsJSONArray.length(); i++) {
                    characteristics.add(characteristicsJSONArray.getString(i));
                }

                JSONArray commonAreaCharacteristicsJSONArray = parent.optJSONArray("CaracteristicasComum");
                ArrayList<String> commonAreaCharacteristics = new ArrayList<>();
                for (int i = 0; i < commonAreaCharacteristicsJSONArray.length(); i++) {
                    commonAreaCharacteristics.add(commonAreaCharacteristicsJSONArray.getString(i));
                }

                realty = new Realty(parent.optInt("CodImovel"), parent.optString("TipoImovel"), address, parent.optDouble("PrecoVenda"), parent.optInt("Dormitorios"),
                        parent.optInt("Suites"), parent.optInt("Vagas"), parent.optDouble("AreaUtil"), parent.optDouble("AreaTotal"), date, client, parent.optString("UrlImagem"),
                        parent.optString("SubTipoOferta"), parent.optString("SubtipoImovel"), pictures, parent.optString("Observacao"), characteristics, parent.optDouble("PrecoCondominio"), commonAreaCharacteristics);

            }


        } catch (JSONException e) {
            Log.e(LOG_TAG, "Error parsing Json.", e);
        } finally {
            return realty;
        }
    }
}

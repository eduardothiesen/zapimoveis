package br.com.thiesen.zapimoveis.network;

import android.content.Context;

import br.com.thiesen.zapimoveis.R;

/**
 * Created by eduardothiesen on 06/04/17.
 */

public class ErrorParser {

    //TODO: Levantar os erros possíveis e tratar eles de maneira devida
    //Tratamento de erro genérico
    public static String parseError(Context context, String error) {
        return context.getString(R.string.generic_error);
    }
}

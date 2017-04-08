package br.com.thiesen.zapimoveis.data;

import java.io.Serializable;

/**
 * Created by eduardothiesen on 06/04/17.
 */

public class Client implements Serializable {
    private int code;
    private String name;

    public Client(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

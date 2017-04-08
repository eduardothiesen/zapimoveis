package br.com.thiesen.zapimoveis.data;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * Created by eduardothiesen on 06/04/17.
 */

public class Address implements Serializable {

    private String street;
    private String number;
    private String complement;
    private String zipCode;
    private String neighborhood;
    private String city;
    private String state;
    private String zone;

    public Address(String street, String number, String complement, String zipCode, String neighborhood, String city, String state, String zone) {
        this.street = street;
        this.number = number;
        this.complement = complement;
        this.zipCode = zipCode;
        this.neighborhood = neighborhood;
        this.city = city;
        this.state = state;
        this.zone = zone;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public String getFormattedAddres() {
        StringBuilder stringBuilder = new StringBuilder();

        if (!street.isEmpty() && street != null) {
            stringBuilder.append(StringUtils.capitalize(street));
        }

        if (!number.isEmpty() && number != null) {
            if (!stringBuilder.toString().isEmpty()) {
                stringBuilder.append(", ");
                stringBuilder.append(StringUtils.capitalize(number));
            }
        }

        if (!complement.isEmpty() && complement != null) {
            if (!stringBuilder.toString().isEmpty()) {
                stringBuilder.append(", ");
            }
            stringBuilder.append(StringUtils.capitalize(complement));
        }

        if (!neighborhood.isEmpty() && neighborhood != null) {
            if (!stringBuilder.toString().isEmpty()) {
                stringBuilder.append(" - ");
            }

            stringBuilder.append(StringUtils.capitalize(neighborhood));
        }

        if (!zone.isEmpty() && zone != null) {
            if (!stringBuilder.toString().isEmpty()) {
                stringBuilder.append(" - ");
            }
            stringBuilder.append(StringUtils.capitalize(zone));
        }

        if (!city.isEmpty() && city != null) {
            if (!stringBuilder.toString().isEmpty()) {
                stringBuilder.append(" - ");
            }

            stringBuilder.append(StringUtils.capitalize(city));
        }

        if (!state.isEmpty() && state != null) {
            if (!stringBuilder.toString().isEmpty()) {
                stringBuilder.append(", ");
            }

            stringBuilder.append(StringUtils.capitalize(state));
        }

        if (!zipCode.isEmpty() && zipCode != null) {
            if (!stringBuilder.toString().isEmpty()) {
                stringBuilder.append(" - ");
            }
            stringBuilder.append(StringUtils.capitalize(zipCode));
        }

        return stringBuilder.toString();
    }
}

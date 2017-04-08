package br.com.thiesen.zapimoveis.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by eduardothiesen on 06/04/17.
 */

public class Realty implements Serializable {
    private int realtyCode;
    private String realtyType;
    private Address address;
    private double price;
    private int numberOfBedrooms;
    private int numberOfSuites;
    private int parkingLotSpaces;
    private double squareFootage;
    private double totalSquareFootage;
    private Date updateDate;
    private Client client;
    private String imageUrl;
    private String offerType;
    private String realtySubtype;

    private ArrayList<String> pictures;
    private String description;
    private ArrayList<String> characteristics;
    private double condominiumPrice;
    private ArrayList<String> commonAreaCharacteristics;


    public Realty(int realtyCode, String realtyType, Address address, double price, int numberOfBedrooms, int numberOfSuites, int parkingLotSpaces,
                     double squareFootage, double totalSquareFootage, Date updateDate, Client client, String imageUrl, String offerType, String realtySubtype) {
        this.realtyCode = realtyCode;
        this.realtyType = realtyType;
        this.address = address;
        this.price = price;
        this.numberOfBedrooms = numberOfBedrooms;
        this.numberOfSuites = numberOfSuites;
        this.parkingLotSpaces = parkingLotSpaces;
        this.squareFootage = squareFootage;
        this.totalSquareFootage = totalSquareFootage;
        this.updateDate = updateDate;
        this.client = client;
        this.imageUrl = imageUrl;
        this.offerType = offerType;
        this.realtySubtype = realtySubtype;
    }

    public Realty(int realtyCode, String realtyType, Address address, double price, int numberOfBedrooms, int numberOfSuites, int parkingLotSpaces,
                    double squareFootage, double totalSquareFootage, Date updateDate, Client client, String imageUrl, String offerType, String realtySubtype,
                  ArrayList<String> pictures, String description, ArrayList<String> characteristics, double condominiumPrice, ArrayList<String> commonAreaCharacteristics) {
        this.realtyCode = realtyCode;
        this.realtyType = realtyType;
        this.address = address;
        this.price = price;
        this.numberOfBedrooms = numberOfBedrooms;
        this.numberOfSuites = numberOfSuites;
        this.parkingLotSpaces = parkingLotSpaces;
        this.squareFootage = squareFootage;
        this.totalSquareFootage = totalSquareFootage;
        this.updateDate = updateDate;
        this.client = client;
        this.imageUrl = imageUrl;
        this.offerType = offerType;
        this.realtySubtype = realtySubtype;

        this.pictures = pictures;
        this.description = description;
        this.characteristics = characteristics;
        this.condominiumPrice = condominiumPrice;
        this.commonAreaCharacteristics = commonAreaCharacteristics;
    }

    public int getRealtyCode() {
        return realtyCode;
    }

    public void setRealtyCode(int realtyCode) {
        this.realtyCode = realtyCode;
    }

    public String getRealtyType() {
        return realtyType;
    }

    public void setRealtyType(String realtyType) {
        this.realtyType = realtyType;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getNumberOfBedrooms() {
        return numberOfBedrooms;
    }

    public void setNumberOfBedrooms(int numberOfBedrooms) {
        this.numberOfBedrooms = numberOfBedrooms;
    }

    public int getNumberOfSuites() {
        return numberOfSuites;
    }

    public void setNumberOfSuites(int numberOfSuites) {
        this.numberOfSuites = numberOfSuites;
    }

    public int getParkingLotSpaces() {
        return parkingLotSpaces;
    }

    public void setParkingLotSpaces(int parkingLotSpaces) {
        this.parkingLotSpaces = parkingLotSpaces;
    }

    public double getSquareFootage() {
        return squareFootage;
    }

    public void setSquareFootage(double squareFootage) {
        this.squareFootage = squareFootage;
    }

    public double getTotalSquareFootage() {
        return totalSquareFootage;
    }

    public void setTotalSquareFootage(double totalSquareFootage) {
        this.totalSquareFootage = totalSquareFootage;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getOfferType() {
        return offerType;
    }

    public void setOfferType(String offerType) {
        this.offerType = offerType;
    }

    public String getRealtySubtype() {
        return realtySubtype;
    }

    public void setRealtySubtype(String realtySubtype) {
        this.realtySubtype = realtySubtype;
    }

    public ArrayList<String> getPictures() {
        return pictures;
    }

    public void setPictures(ArrayList<String> pictures) {
        this.pictures = pictures;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<String> getCharacteristics() {
        return characteristics;
    }

    public void setCharacteristics(ArrayList<String> characteristics) {
        this.characteristics = characteristics;
    }

    public double getCondominiumPrice() {
        return condominiumPrice;
    }

    public void setCondominiumPrice(double condominiumPrice) {
        this.condominiumPrice = condominiumPrice;
    }

    public ArrayList<String> getCommonAreaCharacteristics() {
        return commonAreaCharacteristics;
    }

    public void setCommonAreaCharacteristics(ArrayList<String> commonAreaCharacteristics) {
        this.commonAreaCharacteristics = commonAreaCharacteristics;
    }
}

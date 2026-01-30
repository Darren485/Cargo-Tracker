package com.darren.cargotracker;

public class Client {

    private String clientID;// Unique identifier for the client
    private String clientType;// Individual or Company
    private String clientName;// Name of the client
    private String clientNumber;//phone number of the client
    private String clientEmail;// Email of the client
    private String clientAddress;// Address of the client
    private String city;// City of the client
    private String country;// Country of the client
    private String postalCode;// Postal code of the client

    public Client() {
    }

    public Client(String clientID, String clientType, String clientName, String clientNumber, String clientEmail, String clientAddress, String city, String country, String postalCode) {
        this.clientID = clientID;
        this.clientType = clientType;
        this.clientName = clientName;
        this.clientNumber = clientNumber;
        this.clientEmail = clientEmail;
        this.clientAddress = clientAddress;
        this.city = city;
        this.country = country;
        this.postalCode = postalCode;
    }

    //Get methods
    public String getClientID() {
        return clientID;
    }

    public String getClientType() {
        return clientType;
    }

    public String getClientName() {
        return clientName;
    }

    public String getClientNumber() {
        return clientNumber;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public String getClientAddress() {
        return clientAddress;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getPostalCode() {
        return postalCode;
    }

    //Set method
    public void setClientID(String clientID) {
        this.clientID = clientID;
    }

    public void setClientType(String clientType) {
        this.clientType = clientType;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public void setClientNumber(String clientNumber) {
        this.clientNumber = clientNumber;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    public void setClientAddress(String clientAddress) {
        this.clientAddress = clientAddress;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    @Override
    public String toString() {
        return "Client [clientID=" + clientID + ", clientType=" + clientType + ", clientName=" + clientName
                + ", clientNumber=" + clientNumber + ", clientEmail=" + clientEmail + ", clientAddress=" + clientAddress
                + ", city=" + city + ", country=" + country + ", postalCode=" + postalCode + "]";
    }

}

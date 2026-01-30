package com.darren;


import java.time.LocalDate;
public class ImportShipment {

    private String shipmentId;
    private String shipmentName;
    private LocalDate shipmentDate;
    private String countryOfOrigin;
    private int quantity;
    private double unitPrice;
    private String supplierName;
    private String clientName;
    private String clientNumber;  // added this since you're asking for it now

    public ImportShipment() {
    }

    public ImportShipment(String shipmentId, String shipmentName, LocalDate shipmentDate, String countryOfOrigin, int quantity, double unitPrice, String supplierName, String clientName, String clientNumber) {
        this.shipmentId = shipmentId;
        this.shipmentName = shipmentName;
        this.shipmentDate = shipmentDate;
        this.countryOfOrigin = countryOfOrigin;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.supplierName = supplierName;
        this.clientName = clientName;
        this.clientNumber = clientNumber;
    }

    public String getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(String shipmentId) {
        this.shipmentId = shipmentId;
    }

    public String getShipmentName() {
        return shipmentName;
    }

    public void setShipmentName(String shipmentName) {
        this.shipmentName = shipmentName;
    }

    public LocalDate getShipmentDate() {
        return shipmentDate;
    }

    public void setShipmentDate(LocalDate shipmentDate) {
        this.shipmentDate = shipmentDate;
    }

    public String getCountryOfOrigin() {
        return countryOfOrigin;
    }

    public void setCountryOfOrigin(String countryOfOrigin) {
        this.countryOfOrigin = countryOfOrigin;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientNumber() {
        return clientNumber;
    }

    public void setClientNumber(String clientNumber) {
        this.clientNumber = clientNumber;
    }

    public double calculateTotal() {
        return quantity * unitPrice;
    }

    @Override
    public String toString() {
        return "ImportShipment [shipmentId=" + shipmentId + ", shipmentName=" + shipmentName
                + ", shipmentDate=" + shipmentDate + ", countryOfOrigin=" + countryOfOrigin
                + ", quantity=" + quantity + ", unitPrice=" + unitPrice
                + ", supplierName=" + supplierName + ", clientName=" + clientName
                + ", clientNumber=" + clientNumber + "]";
    }
}

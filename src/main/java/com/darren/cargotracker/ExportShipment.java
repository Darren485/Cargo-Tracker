package com.darren.cargotracker;

import java.time.LocalDate;
import com.darren.cargotracker.ShipmentStatus;


public class ExportShipment {

    private String exportID;
    private String productName;
    private LocalDate exportDate;
    private String destination;
    private int quantity;
    private double unitPrice;
    private String clientName;
    private String clientNumber;
    private String shippingmethod;
    private ShipmentStatus status;
    public ExportShipment() {
    }

    

    public ExportShipment(ShipmentStatus status) {
        this.status = ShipmentStatus.PENDING;
    }



    public ExportShipment(String exportID, String productName, String exportDate, String destination, int quantity, double unitPrice, String clientName, String clientNumber, String shippingmethod) {
        this.exportID = exportID;
        this.productName = productName;
        this.exportDate = LocalDate.parse(exportDate);
        this.destination = destination;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.clientName = clientName;
        this.clientNumber = clientNumber;
        this.shippingmethod = shippingmethod;
        this.status = ShipmentStatus.PENDING;
    }

    public String getExportID() {
        return exportID;
    }

    public void setExportID(String exportID) {
        this.exportID = exportID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public LocalDate getExportDate() {
        return exportDate;
    }

    public void setExportDate(LocalDate exportDate) {
        this.exportDate = exportDate;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
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

    public String getShippingmethod() {
        return shippingmethod;
    }

    public void setShippingmethod(String shippingmethod) {
        this.shippingmethod = shippingmethod;
    }

    public double calculateTotal() {
        return quantity * unitPrice;
    }

    @Override
    public String toString() {
        return "ExportShipment [exportID=" + exportID + ", productName=" + productName + ", exportDate=" + exportDate
                + ", destination=" + destination + ", quantity=" + quantity + ", unitPrice=" + unitPrice
                + ", clientName=" + clientName + ", clientNumber=" + clientNumber + ", shippingmethod=" + shippingmethod
                + "]";
    }


    
}

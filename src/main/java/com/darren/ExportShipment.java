package com.darren.cargotracker;


import java.time.LocalDate;
import com.darren.AbstractShipment;
import com.darren.ShipmentStatus;


public class ExportShipment extends AbstractShipment {

    private String destination;
    private String exportLicense;
    private String incoterms;
    private String clientName;
    private String clientNumber;
    private String shippingmethod;

    public ExportShipment() {
        super();
        setStatus(ShipmentStatus.PENDING);

    }

    public ExportShipment(String exportID, String productName, LocalDate exportDate, String destination, int quantity, double unitPrice, String clientName, String clientNumber, String shippingmethod, String destinationCountry, String exportLicense, String incoterms) {
        super(exportID, productName, exportDate, quantity, unitPrice, ShipmentStatus.PENDING, null);
        this.destination = destination;
        this.clientName = clientName;
        this.clientNumber = clientNumber;
        this.shippingmethod = shippingmethod;
        this.exportLicense = exportLicense;
        this.incoterms = incoterms;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getExportLicense() {
        return exportLicense;
    }

    public void setExportLicense(String exportLicense) {
        this.exportLicense = exportLicense;
    }

    public String getIncoterms() {
        return incoterms;
    }

    public void setIncoterms(String incoterms) {
        this.incoterms = incoterms;
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

    @Override
    public String getShipmentDetails() {
        return "Export to " + destination + " for client " + clientName;
    }

    @Override
    public String toString() {
        return "ExportShipment ["
                + "ShipmentID=" + getShipmentID() // From parent
                + ", Product=" + getProductName() // From parent
                + ", Date=" + getShipmentDate() // From parent
                + ", Destination=" + destination
                + ", Quantity=" + getQuantity() // From parent
                + ", UnitPrice=" + getUnitPrice() // From parent
                + ", Client=" + clientName
                + ", Total=" + calculateTotalValue() // From parent
                + "]";
    }

}

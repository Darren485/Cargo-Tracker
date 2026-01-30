package com.darren.cargotracker;

import java.time.LocalDate;

public abstract class AbstractShipment {

    private String shipmentID;
    private String productName;
    private LocalDate shipmentDate;
    private Integer quantity;
    private Double unitPrice;
    private ShipmentStatus status;
    private String trackingNumber;

    public AbstractShipment() {
    }

    public AbstractShipment(String shipmentID, String productName, LocalDate shipmentDate, Integer quantity, Double unitPrice, ShipmentStatus status, String trackingNumber) {
        this.shipmentID = shipmentID;
        this.productName = productName;
        this.shipmentDate = shipmentDate;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.status = status;
        this.trackingNumber = trackingNumber;
    }

    public String getShipmentID() {
        return shipmentID;
    }

    public void setShipmentID(String shipmentID) {
        this.shipmentID = shipmentID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public LocalDate getShipmentDate() {
        return shipmentDate;
    }

    public void setShipmentDate(LocalDate shipmentDate) {
        this.shipmentDate = shipmentDate;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public ShipmentStatus getStatus() {
        return status;
    }

    public void setStatus(ShipmentStatus status) {
        this.status = status;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    public abstract String getShipmentDetails();

    public Double calculateTotalValue() {
        return quantity * unitPrice;
    }

    public boolean isShipmentDelayed(LocalDate expectedDeliveryDate) {
        return LocalDate.now().isAfter(expectedDeliveryDate);
    }
}

package com.darren.cargotracker;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Shipment {

    private String shipmentID;
    private Type type; // AIR / SEA / LAND(ENUM)
    private String referenceNumber; // BOL/AWB
    private String description;
    private Status status; // BOOKED, LOADED, IN_TRANSIT, AT_PORT, CUSTOMS_HOLD, DELIVERED
    private LocalDate date;
    private String carrier;
    private String currentLocation;
    private double value;
    private double weight;
    private Priority priority;

    //Constructor
    public Shipment(String shipmentID, String referenceNumber, String customerInfo, LocalDate eta, LocalDateTime etd, LocalDateTime ata, DelayReason delayReason) {
        this.shipmentID = shipmentID;
        this.referenceNumber = referenceNumber;
        this.type = type;
        this.description = description;
        this.status = status;
        this.date = date;
        this.carrier = carrier;
        this.currentLocation = currentLocation;
        this.value = value;
        this.weight = weight;
        this.priority = priority;
    }

    //Getters
    public String getShipmentID() {
        return shipmentID;
    }

    public String getReferenceNumber() {
        return referenceNumber;
    }

    public String getCustomerInfo() {
        return customerInfo;
    }

    //Setters
    public void setShipmentID(String shipmentID) {
        this.shipmentID = shipmentID;
    }

    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }

    public void setCustomerInfo(String customerInfo) {
        this.customerInfo = customerInfo;
    }

    public void setEta(LocalDate eta) {
        this.eta = eta;
    }

    public void setEtd(LocalDateTime etd) {
        this.etd = etd;
    }

    public void setAta(LocalDateTime ata) {
        this.ata = ata;
    }

    public void setDelayReason(DelayReason delayReason) {
        this.delayReason = delayReason;
    }

    //Calculate ETA(Expected Time of Arrival)
    public LocalDateTime calculateETA() {
        // Implementation for calculating ETA
        LocalDateTime now = LocalDateTime.now();

        switch (currentStatus) {
            case BOOKED:
                return now.plusDays(7); //Takes 7 days from booking
                break;

            case IN_TRANSIT:
                // Implementation for calculating ETA when in transit
                if (transportType == TransportType.AIR) {
                    return now.plusDays(2); //Takes 2 days for air transport
                } else if (transportType == TransportType.SEA) {
                    return now.plusDays(14); //Takes 14 days for sea transport
                } else if (transportType == TransportType.ROAD) {
                    return now.plusDays(7); //Takes 7 days for road transport
                }
                break;

            case AT_PORT:
                return now.plusDays(3); //3 days at the Port
                break;

            case CUSTOM_HOLD:
                return now.plusDays(5); // Customs takes time
                break;

            case DELAYED:
                return now.plusDays(10); //10 days for delay
                break;

            case DELIVERED:
                return actualArrivedTime; //Already arrived
                break;

            default:
                return now.plusDays(10); //Default 
                break;
        }
        //Update Status
    public void updateStatus(Status newStatus, String notes) {
        //Store old status
        Status oldStatus = this.currentStatus;

        //Update Status
        this.currentStatus = newStatus;
        this.lastUpdated = LocalDateTime.now();

        //Add to history
        StatusHistoryEntry entry = new StatusHistoryEntry(oldStatus, newStatus, notes, LocalDateTime.now(), this.currentLocation, "system");
        this.statusHistory.add(entry);

        //Update ETA id needed
        if (newStatus == Status.CUSTOM_HOLD) {
            this.eta = this.eta.plusDays(3);
        }

        //Trigger notifications
        NotificationSystem.notifyStatusChange(this, oldStatus, newStatus);
    }

    //Get current location
    public String getCurrentLocation() {
        //If delivered show final location
        if (currentStatus == Status.DELIVERED) {
            return "Delivered to: " + destination;
        }

        //If in transit, show progress
        if (currentStatus == Status.IN_TRANSIT) {
            double progress = calculateProgress();
            return String.format("%s -> %s (%d%%)", origin, destination, (int) (progress * 100));
        }

        //If at specific facility with details
        if (currentFacility != null && gateorBerthNumber != null) {
            return String.format("%s - %s %s", currentFacility.getName(), currentFacility.getType() == FacilityType.PORT ? "Gate" : "Berth", gateorBerthNumber);
        }

        //If at airport/port
        if (currentLocation != null) {
            if (currentLocation.toLowerCase().contains("port")) {
                return "Port: " + currentLocation;
            } else if (currentLocation.toLowerCase().contains("airport")) {
                return "Airport: " + currentLocation;
            }
            return currentLocation;
        }
        //Default
        return "Unknown Location";



    private double
}

}
}

package com.darren;

public class Port {

    private PortCode portCode;
    private String country;
    private Double handlingFee;
    private Integer dailyCapacity;
    private Integer currentLoad;

    public Port(PortCode portCode, String country, Double handlingFee, Integer dailyCapacity, Integer currentLoad) {
        this.portCode = portCode;
        this.country = country;
        this.handlingFee = handlingFee;
        this.dailyCapacity = dailyCapacity;
        this.currentLoad = currentLoad;
    }

    public PortCode getPortCode() {
        return portCode;
    }

    public void setPortCode(PortCode portCode) {
        this.portCode = portCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Double getHandlingFee() {
        return handlingFee;
    }

    public void setHandlingFee(Double handlingFee) {
        this.handlingFee = handlingFee;
    }

    public Integer getDailyCapacity() {
        return dailyCapacity;
    }

    public void setDailyCapacity(Integer dailyCapacity) {
        this.dailyCapacity = dailyCapacity;
    }

    public Integer getCurrentLoad() {
        return currentLoad;
    }

    public void setCurrentLoad(Integer currentLoad) {
        this.currentLoad = currentLoad;
    }

    public boolean canHandleMoreShips() {
        return currentLoad < (dailyCapacity != null ? dailyCapacity : 50);
    }

    public void addShip() {
        if (canHandleMoreShips()) {
            currentLoad++;
        }
    }

    public void removeShip() {
        if (currentLoad > 0) {
            currentLoad--;
        }
    }

    public double calculatePortCharges(double shipmentValue) {
        double baseFee = handlingFee != null ? handlingFee : 500.0;
        return baseFee + (shipmentValue * 0.02); // 2% of shipment value

    }

    public String getPortInfo() {
            return portCode.name() + " - " + portCode.getDescription() +  // getDescription() NOT getPortName()
           " (" + country + ") - Fee: $" + handlingFee; 
    }

}

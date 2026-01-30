package com.darren.cargotracker;

public class ExportRepository {

    public void saveExport(ExportShipment shipment) {

        System.out.println("Export shipment with ID " + shipment.getExportID() + " saved.");
    }
}
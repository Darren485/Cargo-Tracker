package com.darren;

import java.util.*;
import com.darren.ExportShipment;
public class ExportRepository {

    public void saveExport(ExportShipment shipment) {

        System.out.println("Export shipment with ID " + shipment.getShipmentID() + " saved.");
    }
}
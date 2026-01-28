package com.darren.cargotracker;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

;

public class ExportService {

    private static final String EXPORT_TYPE = "Export.txt";

    public ExportService() {
    }

    public void recordExport(Scanner input) {
        ExportShipment shipment = new ExportShipment("", "", "", "", 0, 0.0, "", "", "");

        System.out.println("\n=== Record New Export Shipment ===");

        System.out.print("Enter the Export ID: ");
        shipment.setExportID(input.nextLine().trim());

        System.out.print("Enter the Product Name: ");
        shipment.setProductName(input.nextLine().trim());

        System.out.print("Enter the Export Date (format: yyyy-MM-dd, e.g. 2025-01-28): ");
        shipment.setExportDate(input.nextLine().trim());

        System.out.print("Enter the Destination: ");
        shipment.setDestination(input.nextLine().trim());

        System.out.print("Enter the Quantity: ");
        while (!input.hasNextInt()) {
            System.out.println("Invalid quantity. Please enter a number.");
            input.next(); // clear invalid input
        }
        shipment.setQuantity(input.nextInt());
        input.nextLine(); // consume newline after nextInt()

        System.out.print("Enter the Unit Price: ");
        while (!input.hasNextDouble()) {
            System.out.println("Invalid unit price. Please enter a number.");
            input.next();
        }
        shipment.setUnitPrice(input.nextDouble());
        input.nextLine(); // consume newline

        System.out.print("Enter the Client Name: ");
        shipment.setClientName(input.nextLine().trim());

        while (true) {
            try {
                System.out.print("Enter the Client Phone Number: ");
                String clientNumber = input.nextLine().trim();
                validatephoneNumber(clientNumber);
                shipment.setClientNumber(clientNumber);
                break;
            } catch (IncompleteNumberException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        System.out.print("Enter the Shipping Method: ");
        shipment.setShippingmethod(input.nextLine().trim());

        System.out.println("\n=== Export Shipment Created Successfully! ===");
        System.out.println(shipment.toString());
        System.out.printf("Total Value: %.2f%n", shipment.calculateTotal());
        System.out.println("=======================================");
    }

    //CUSTOM EXCEPTION CLASSES
    public static class IncompleteNumberException extends Exception {

        public IncompleteNumberException(String message) {
            super(message);
        }

    }
    //METHOD FOR ALL THE CUSTOM EXCEPTION CLASSES

    public void validatephoneNumber(String clientNumber) throws IncompleteNumberException {
        if (clientNumber == null || clientNumber.length() != 11) {
            throw new IncompleteNumberException("Phone number is incomplete. It must be 11 digits");
        }
    }

}

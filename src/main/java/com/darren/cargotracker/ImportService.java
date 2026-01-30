package com.darren.cargotracker;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

import exception.IncompleteNumberException;

public class ImportService {

    public void recordImport(Scanner input) {
        ImportShipment shipment = new ImportShipment();

        System.out.println("\n=== Record New Import Shipment ===");

        System.out.print("Enter shipment ID: ");
        shipment.setShipmentId(input.nextLine().trim());

        System.out.print("Enter shipment name: ");
        shipment.setShipmentName(input.nextLine().trim());

        System.out.print("Enter shipment date (yyyy-MM-dd): ");
        String dateStr = input.nextLine().trim();
        try {
            shipment.setShipmentDate(LocalDate.parse(dateStr));
        } catch (Exception e) {
            System.out.println("Invalid date format. Using today.");
            shipment.setShipmentDate(LocalDate.now());
        }

        System.out.print("Enter country of origin: ");
        shipment.setCountryOfOrigin(input.nextLine().trim());

        System.out.print("Enter quantity: ");
        while (!input.hasNextInt()) {
            System.out.println("Invalid quantity. Enter a number:");
            input.next();
        }
        shipment.setQuantity(input.nextInt());
        input.nextLine();

        System.out.print("Enter unit price: ");
        while (!input.hasNextDouble()) {
            System.out.println("Invalid price. Enter a number:");
            input.next();
        }
        shipment.setUnitPrice(input.nextDouble());
        input.nextLine();

        System.out.print("Enter supplier name: ");
        shipment.setSupplierName(input.nextLine().trim());

        System.out.print("Enter client name: ");
        shipment.setClientName(input.nextLine().trim());

        while (true) {
            try {
                System.out.print("Enter client phone number: ");
                String clientNumber = input.nextLine().trim();
                Validator.validatePhoneNumber(clientNumber); // static call – no new Validator()
                shipment.setClientNumber(clientNumber);
                break;
            } catch (IncompleteNumberException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        System.out.println("\n=== Import Shipment Created Successfully! ===");
        System.out.println(shipment.toString());
        System.out.printf("Total Value: %.2f%n", shipment.calculateTotal());
        System.out.println("=======================================");

        // Later: ImportRepository repo = new ImportRepository();
        // repo.saveImport(shipment);
        System.out.println("Import shipment recorded (ready for repository save).");
    }

}

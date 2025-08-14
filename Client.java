import java.io.*;
import java.util.*;

public class Client {
    private String clientID;// Unique identifier for the client
    private String clientType;// Individual or Company
    private String clientName;// Name of the client
    private String clientNumber;//phone number of the client
    private String clientEmail;// Email of the client
    private String clientAddress;// Address of the client
    private String city;// City of the client
    private String country;// Country of the client
    private String postalCode;// Postal code of the client
    private static final String CLIENT_TYPE = "Client.txt";
    private static int shipmentCounter = 1;    
    private static int exportCounter = 1;
    private List<String> orderHistory = new ArrayList<>();

    public Client(String clientID, String clientType, String clientName, String clientNumber, String clientEmail, String clientAddress, String city, String country, String postalCode) {
        this.clientID = clientID;
        this.clientType = clientType;
        this.clientName = clientName;
        this.clientNumber = clientNumber;
        this.clientEmail = clientEmail;
        this.clientAddress = clientAddress;
        this.city = city;
        this.country = country;
        this.postalCode = postalCode;
    }

    //Get methods
    public String getClientID() {
        return clientID;
    }
    public String getClientType() {
        return clientType;
    }
    public String getClientName() {
        return clientName;
    }
    public String getClientNumber() {
        return clientNumber;
    }
    public String getClientEmail() {
        return clientEmail;
    }
    public String getClientAddress() {
        return clientAddress;
    }
    public String getCity() {
        return city;
    }
    public String getCountry() {
        return country;
    }
    public String getPostalCode() {
        return postalCode;
    }

    
    
    //Set method
    public void setClientID(String clientID){
        this.clientID = clientID;
    }
    public void setClientType(String clientType){
        this.clientType = clientType;
    }
    public void setClientName(String clientName){
        this.clientName = clientName;
    }
    public void setClientNumber(String clientNumber){
        this.clientNumber = clientNumber;
    }
    public void setClientEmail(String clientEmail){
        this.clientEmail = clientEmail;
    }
    public void setClientAddress(String clientAddress){
        this.clientAddress = clientAddress;
    }
    public void setCity(String city){
        this.city = city;
    }
    public void setCountry(String country){
        this.country = country;
    }
    public void setPostalCode(String postalCode){
        this.postalCode = postalCode;
    }


    //Method to show/add client details
    public void viewDetails(Scanner input) {
        try(FileWriter fw = new FileWriter(CLIENT_TYPE, true);
            BufferedWriter bw = new BufferedWriter(fw)){
                System.out.println("Enter Client ID: ");
        setClientID(input.nextLine().trim());

        try {
        System.out.println("Enter Client Type (Individual/Company): ");
        String clientType = input.nextLine().trim();
        validateClientType(clientType);
        setClientType(clientType);
        } catch (InvalidClientTypeException e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("Enter Client Name: ");
        setClientName(input.nextLine().trim());

        try {
        System.out.println("Enter Client Phone Number: ");
        String phoneNumber = input.nextLine().trim();
        validatePhoneNumber(phoneNumber);
        setClientNumber(phoneNumber);
        } catch (InvalidPhoneNumberException e) {
        System.out.println(e.getMessage());
        }
        

        try {
         System.out.println("Enter Client Email: ");
         String email = input.nextLine().trim();
         validateClientEmail(email);
         setClientEmail(email);
        } catch (InvalidClientEmailException e) {
            System.out.println("Error: " + e.getMessage());
        }
        System.out.println("Enter Client Address: ");
        setClientAddress(input.nextLine().trim());


        System.out.println("Enter City: ");
        setCity(input.nextLine().trim());

        System.out.println("Enter Country: ");
        setCountry(input.nextLine().trim());

        System.out.println("Enter Postal Code: ");
        setPostalCode(input.nextLine().trim());

        String separator = "-".repeat(40);

        bw.write("CLIENT DETAILS\n");
        bw.newLine();
        bw.write(separator);
        bw.newLine();
        bw.write("Client ID: " + getClientID());
        bw.newLine();
        bw.write("Client Type: " + getClientType());
        bw.newLine();
        bw.write("Client Name: " + getClientName());
        bw.newLine();
        bw.write("Client Number: " + getClientNumber());
        bw.newLine();
        bw.write("Client Email: " + getClientEmail());
        bw.newLine();
        bw.write("Client Address: " + getClientAddress());
        bw.newLine();
        bw.write("City: " + getCity());
        bw.newLine();
        bw.write("Country: " + getCountry());
        bw.newLine();
        bw.write("Postal Code: " + getPostalCode());
        bw.newLine();
        bw.write(separator);
        bw.newLine();
        bw.newLine();
        System.out.println("Client details saved to " + CLIENT_TYPE);
            }catch(IOException e){
                System.out.println("Error: " + e.getMessage());
            }
        }



//Method to generate a new Import Shipment ID
private static  String generateShipmentID(){
    return "IMP-" + String.format("%03d", shipmentCounter++);
}


//Method to generate a new Export Shipment ID
private static String generateExportID(){
    return "EXP-" + String.format("%03d", exportCounter++);
}


//Method to import goods
public void ImportGoods(Scanner input){
    System.out.println("\n===Import Goods for Client: " + this.clientName + "===");

    String shipmentID = generateShipmentID();
    System.out.println("Generated Shipment ID: " + shipmentID);

    System.out.println("Enter Shipment name: ");
    String shipmentName = input.nextLine().trim();


    System.out.println("Enter Shipment Date");
    String shipmentDate = input.nextLine().trim();

    System.out.println("Enter country of origin:");
    String countryOfOrigin = input.nextLine().trim();

    System.out.print("Enter quantity: ");
    while (!input.hasNextInt()) {
    System.out.println("Invalid input. Please enter a valid quantity:");
    input.next();
    }
    int quantity = input.nextInt();
    input.nextLine();

    System.out.print("Enter unit price: ");
    while (!input.hasNextDouble()) {
    System.out.println("Invalid input. Please enter a valid price:");
    input.next();
    }
    double unitPrice = input.nextDouble();
    input.nextLine();

    System.out.print("Enter Supplier Name: ");
    String supplierName = input.nextLine().trim();

    ImportHandler importer = new ImportHandler(shipmentID, shipmentName, shipmentDate, countryOfOrigin, unitPrice, quantity, supplierName, this.clientName);
    importer.recordImport(input);
    System.out.println("Goods imported successfully!");
}






public void exportGoods(Scanner input) {
    // Create ExportHandler and pass this client's details
    ExportHandler exportHandler = new ExportHandler(
        "", // export ID will be generated here
        "", "", "", 0, 0.0,
        this.clientName,         // from Client
        this.clientNumber,
        ""
    );

    // Generate export ID
    String exportID = generateExportID();
    exportHandler.setexportID(exportID);
    System.out.println("Generated Export ID: " + exportID);

    // Ask for export details
    System.out.println("Enter product name: ");
    exportHandler.setProductName(input.nextLine().trim());

    System.out.println("Enter export date (yyyy-MM-dd): ");
    exportHandler.setExportDate(input.nextLine().trim());

    System.out.println("Enter destination: ");
    exportHandler.setDestination(input.nextLine().trim());

    System.out.println("Enter quantity: ");
    while (!input.hasNextInt()) {
        System.out.println("Invalid quantity. Please enter a valid number.");
        input.next();
    }
    exportHandler.setQuantity(input.nextInt());
    input.nextLine(); // consume newline

    System.out.println("Enter unit price: ");
    while (!input.hasNextDouble()) {
        System.out.println("Invalid unit price. Please enter a valid number.");
        input.next();
    }
    exportHandler.setUnitPrice(input.nextDouble());
    input.nextLine();

    System.out.println("Enter shipping method: ");
    exportHandler.setShippingmethod(input.nextLine().trim());

    // Finally save to file
    exportHandler.recordExport(input);
}

//METHOD TO SEE CLIENT HISTORY
public void showOrderHistory() {
    System.out.println("\n=== Order History for Client: " + this.clientName + " ===\n");

    showOrdersFromFile("Imports.txt", "--- IMPORTS ---");
    showOrdersFromFile("Exports.txt", "--- EXPORTS ---");
}

private void showOrdersFromFile(String fileName, String sectionTitle) {
    File file = new File(fileName);

    if (!file.exists()) {
        System.out.println(sectionTitle + "\nNo records found.");
        return;
    }

    System.out.println(sectionTitle);

    try (BufferedReader br = new BufferedReader(new FileReader(file))) {
        String line;
        boolean matchFound = false;
        StringBuilder orderBlock = new StringBuilder();

        while ((line = br.readLine()) != null) {
            if (line.trim().isEmpty()) {
                // End of one order block — check if it matches this client
                if (orderBlock.toString().contains("Client Name: " + this.clientName)) {
                    System.out.println(orderBlock);
                    matchFound = true;
                }
                orderBlock.setLength(0); // reset for next block
            } else {
                orderBlock.append(line).append("\n");
            }
        }

        // For last order if file doesn't end with blank line
        if (orderBlock.length() > 0 &&
            orderBlock.toString().contains("Client Name: " + this.clientName)) {
            System.out.println(orderBlock);
            matchFound = true;
        }

        if (!matchFound) {
            System.out.println("No " + sectionTitle.toLowerCase() + " found for this client.");
        }

    } catch (IOException e) {
        System.out.println("Error reading file: " + e.getMessage());
    }

    System.out.println();
}

//METHOD TO UPDATE CLIENT DATA
public void updateClientData(Scanner input) {
    File file = new File(CLIENT_TYPE);
    if (!file.exists()) {
        System.out.println("No client records found to update.");
        return;
    }

    System.out.print("Enter Client ID to update: ");
    String targetID = input.nextLine().trim();

    StringBuilder updatedData = new StringBuilder();
    boolean clientFound = false;

    try (BufferedReader br = new BufferedReader(new FileReader(file))) {
        String line;
        StringBuilder clientBlock = new StringBuilder();

        while ((line = br.readLine()) != null) {
            if (line.trim().isEmpty()) {
                // Process the block when empty line (end of a client)
                if (clientBlock.toString().contains("Client ID: " + targetID)) {
                    clientFound = true;
                    System.out.println("\n=== Current Data ===");
                    System.out.println(clientBlock);

                    // Ask new values
                    System.out.print("Enter new Client Name (leave blank to keep current): ");
                    String newName = input.nextLine().trim();
                    if (!newName.isEmpty()) {
                        clientBlock = replaceField(clientBlock, "Client Name", newName);
                    }

                    System.out.print("Enter new Phone Number (leave blank to keep current): ");
                    String newNumber = input.nextLine().trim();
                    if (!newNumber.isEmpty()) {
                        clientBlock = replaceField(clientBlock, "Client Number", newNumber);
                    }

                    System.out.print("Enter new Email (leave blank to keep current): ");
                    String newEmail = input.nextLine().trim();
                    if (!newEmail.isEmpty()) {
                        clientBlock = replaceField(clientBlock, "Client Email", newEmail);
                    }

                    System.out.print("Enter new Address (leave blank to keep current): ");
                    String newAddress = input.nextLine().trim();
                    if (!newAddress.isEmpty()) {
                        clientBlock = replaceField(clientBlock, "Client Address", newAddress);
                    }
                }

                // Append updated or unchanged block
                updatedData.append(clientBlock).append("\n\n");
                clientBlock.setLength(0); // reset
            } else {
                clientBlock.append(line).append("\n");
            }
        }

        // If last block didn't end with a blank line
        if (clientBlock.length() > 0) {
            if (clientBlock.toString().contains("Client ID: " + targetID)) {
                clientFound = true;
                System.out.println("\n=== Current Data ===");
                System.out.println(clientBlock);
            }
            updatedData.append(clientBlock).append("\n");
        }

    } catch (IOException e) {
        System.out.println("Error reading file: " + e.getMessage());
        return;
    }

    if (!clientFound) {
        System.out.println("No client found with ID: " + targetID);
        return;
    }

    // Write updated data back to file
    try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
        bw.write(updatedData.toString().trim());
    } catch (IOException e) {
        System.out.println("Error writing file: " + e.getMessage());
    }

    System.out.println("Client data updated successfully!");
}

// Helper to replace field in the text block
private StringBuilder replaceField(StringBuilder block, String fieldName, String newValue) {
    String[] lines = block.toString().split("\n");
    for (int i = 0; i < lines.length; i++) {
        if (lines[i].startsWith(fieldName + ":")) {
            lines[i] = fieldName + ": " + newValue;
        }
    }
    return new StringBuilder(String.join("\n", lines));
}


//METHOD TO DELETE ALL CLIENT ORDERS
public void deleteAllClientOrders(Scanner input) {
    System.out.print("Enter Client ID to delete all orders: ");
    String targetID = input.nextLine().trim();

    // Delete from Import Orders file
    deleteOrdersFromFile("ImportOrders.txt", targetID);

    // Delete from Export Orders file
    deleteOrdersFromFile("ExportOrders.txt", targetID);

    System.out.println("All orders for Client ID " + targetID + " have been deleted.");
}

private void deleteOrdersFromFile(String fileName, String targetID) {
    File file = new File(fileName);
    if (!file.exists()) {
        System.out.println(fileName + " does not exist. Skipping...");
        return;
    }

    StringBuilder updatedData = new StringBuilder();

    try (BufferedReader br = new BufferedReader(new FileReader(file))) {
        String line;
        StringBuilder orderBlock = new StringBuilder();
        boolean keepBlock = true;

        while ((line = br.readLine()) != null) {
            if (line.trim().isEmpty()) {
                if (keepBlock) {
                    updatedData.append(orderBlock).append("\n\n");
                }
                orderBlock.setLength(0);
                keepBlock = true; // reset
            } else {
                orderBlock.append(line).append("\n");
                if (line.startsWith("Client ID: ") && line.contains(targetID)) {
                    keepBlock = false; // this block belongs to target client
                }
            }
        }

        // Handle last block
        if (orderBlock.length() > 0 && keepBlock) {
            updatedData.append(orderBlock).append("\n");
        }

    } catch (IOException e) {
        System.out.println("Error reading " + fileName + ": " + e.getMessage());
        return;
    }

    // Write back filtered orders
    try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
        bw.write(updatedData.toString().trim());
    } catch (IOException e) {
        System.out.println("Error writing " + fileName + ": " + e.getMessage());
    }
}



//METHOD THAT FINDS CLIENT ORDER BY DATE
public void searchOrderByDate(Scanner input) {
    System.out.print("Enter order date to search (e.g., 2025-08-12): ");
    String targetDate = input.nextLine().trim();

    boolean found = false;

    System.out.println("\n--- Searching Import Orders ---");
    found |= searchOrdersInFile("ImportOrders.txt", targetDate);

    System.out.println("\n--- Searching Export Orders ---");
    found |= searchOrdersInFile("ExportOrders.txt", targetDate);

    if (!found) {
        System.out.println("No orders found for date: " + targetDate);
    }
}

private boolean searchOrdersInFile(String fileName, String targetDate) {
    File file = new File(fileName);
    if (!file.exists()) {
        System.out.println(fileName + " does not exist.");
        return false;
    }

    boolean found = false;
    try (BufferedReader br = new BufferedReader(new FileReader(file))) {
        String line;
        StringBuilder orderBlock = new StringBuilder();
        boolean match = false;

        while ((line = br.readLine()) != null) {
            if (line.trim().isEmpty()) {
                if (match) {
                    System.out.println(orderBlock.toString());
                    System.out.println("-".repeat(40));
                    found = true;
                }
                orderBlock.setLength(0);
                match = false;
            } else {
                orderBlock.append(line).append("\n");
                if (line.startsWith("Shipment Date: ") && line.contains(targetDate)) {
                    match = true;
                }
            }
        }

        // Handle last block
        if (match) {
            System.out.println(orderBlock.toString());
            System.out.println("-".repeat(40));
            found = true;
        }

    } catch (IOException e) {
        System.out.println("Error reading " + fileName + ": " + e.getMessage());
    }

    return found;
}



//EXCEPTION CLASSES
public static class InvalidPhoneNumberException extends Exception {
    public InvalidPhoneNumberException(String message) {
        super(message);
    }
}

public static class InvalidClientTypeException extends Exception {
    public InvalidClientTypeException(String message) {
        super(message);
    }
}

public static class InvalidClientEmailException extends Exception {
    public InvalidClientEmailException(String message) {
        super(message);
    }
}



//Exeption classes methods

public void validatePhoneNumber(String phoneNumber) throws InvalidPhoneNumberException {
    if (phoneNumber == null || phoneNumber.isEmpty()) {
        throw new InvalidPhoneNumberException("Phone number cannot be empty.");
    }
    if (phoneNumber.length() != 10 || !phoneNumber.matches("\\d+")) {
        throw new InvalidPhoneNumberException("Phone number must be 10 digits long.");
    }

}

public void validateClientType(String clientType) throws InvalidClientTypeException {
    if (clientType == null || clientType.isEmpty()) {
        throw new InvalidClientTypeException("Client type cannot be empty.");
    }
    if (!clientType.equalsIgnoreCase("Individual") && !clientType.equalsIgnoreCase("Company")) {
        throw new InvalidClientTypeException("Client type must be either 'Individual' or 'Company'.");
    }

}


public void validateClientEmail(String clientEmail) throws InvalidClientEmailException {
    if (clientEmail == null || clientEmail.isEmpty()) {
        throw new InvalidClientEmailException("Client email cannot be empty.");
    }
    if (!clientEmail.contains("@") || !clientEmail.contains("gmail.com")) {
        throw new InvalidClientEmailException("Email must contain '@' and 'gmail.com'.");
    }
}

}

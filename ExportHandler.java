import java.io.*;
import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;;

public class ExportHandler {
    private String exportID;
    private String productName;
    private String exportDate;
    private String destination;
    private int quantity;
    private  double unitPrice;
    private String clientName;
    private String clientNumber;
    private String shippingmethod;
     //The file we are writing to
    private static final String EXPORT_TYPE = "Export.txt";

    public ExportHandler(String exportID, String productName, String exportDate, String destination, int quantity, double unitPrice, String clientName, String clientNumber, String shippingmethod){
        this.exportID = exportID;
        this.productName = productName;
        this.exportDate = exportDate;
        this.destination = destination;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.clientName = clientName;
        this.clientNumber = clientNumber;
        this.shippingmethod = shippingmethod;
    }

    //Get methods
    public String getExportID() {
        return exportID;
    }
    public String getProductName() {
        return productName;
    }
    public String getExportDate() {
        return exportDate;
    }
    public String getDestination() {
        return destination;
    }
    public int getQuantity() {
        return quantity;
    }
    public double getUnitPrice() {
        return unitPrice;
    }
    public String getClientName() {
        return clientName;
    }
    public String getClientNumber() {
        return clientNumber;
    }
    public String getShippingmethod() {
        return shippingmethod;
    }  
    public double calculateTotal(){
        return quantity * unitPrice;
    }


    //Set methods
    public void setexportID(String exportID){
        this.exportID = exportID;
    }
    public void setProductName(String productName){
        this.productName = productName;
    }
    public void setExportDate(String exportDate){
        this.exportDate = exportDate;
    }
    public void setDestination(String destination){
        this.destination = destination;
    }
    public void setQuantity(int quantity){
        this.quantity = quantity;
    }
    public void setUnitPrice(double unitPrice){
        this.unitPrice = unitPrice;
    }
    public void setClientName(String clientName){
        this.clientName = clientName;
    }
    public void setClientNumber(String clientNumber){
        this.clientNumber = clientNumber;
    }
    public void setShippingmethod(String shippingmethod){
        this.shippingmethod = shippingmethod;
    }






//Method to add new export
    public void recordExport(Scanner input){
        try(FileWriter fw = new FileWriter(EXPORT_TYPE, true);
            BufferedWriter bw = new BufferedWriter(fw)){

                System.out.println("Enter the export ID: ");
                setexportID(input.nextLine().trim());


                System.out.println("Enter the product name: ");
                setProductName(input.nextLine().trim());

                System.out.println("Enter the export date: ");
                setExportDate(input.nextLine().trim());

                System.out.println("Enter the destination: ");
                setDestination(input.nextLine().trim());

                System.out.println("Enter the quantity: ");
                while(!input.hasNextInt()){
                    System.out.println("Invalid quantity. Please enter a valid quantity.");
                    input.next();
                }
                setQuantity(input.nextInt());
                input.nextLine();

                System.out.println("Enter the unit price: ");
                while(!input.hasNextDouble()){
                    System.out.println("Invalid unit price. Please enter a valid unit price.");
                    input.nextLine();
                }
                setUnitPrice(input.nextDouble());
                input.nextLine();
                

                System.out.println("Enter the client name: ");
                setClientName(input.nextLine().trim());

                while(true){
                    try {
                      System.out.println("Enter the client number:");
                      String clientNumber = input.nextLine().trim();
                      validatephoneNumber(clientNumber);
                      setClientNumber(clientNumber);
                      break;
                    } catch (IncompleteNumberException e) {
                        System.out.println(e.getMessage());
                    }
                }

                System.out.println("Enter the shipping method: ");
                setShippingmethod(input.nextLine().trim());

                //Here we are writing to the text file
                String separator = "-".repeat(40);
                bw.write("EXPORT RECEIPT");
                bw.newLine();
                bw.write(separator);
                bw.newLine();
                bw.write("Export ID: " + exportID);
                bw.newLine();
                bw.write("Product Name: " + productName);
                bw.newLine();
                bw.write("Export Date: " + exportDate);
                bw.newLine();
                bw.write("Destination: " + destination);
                bw.newLine();
                bw.write("Quantity: " + quantity);
                bw.newLine();
                bw.write("Unit Price: " + String.format("%.2f", getUnitPrice()));
                bw.newLine();
                 bw.write("Total Price: " + String.format("%.2f", calculateTotal()));
                 bw.newLine();
                bw.write("Client Name: " + clientName);
                bw.newLine();
                bw.write("Client Number: " + clientNumber);
                bw.newLine();
                bw.write("Shipping Method: " + shippingmethod);
                bw.newLine();
                bw.write(separator);
                bw.newLine();
                bw.newLine();
            }catch (IOException e) {
                 System.out.println("Error writing to file: " + e.getMessage());
            }
    }



//Method to view all exports
public void viewAllExports() {
    int exportCount = 0;
    String separator = "-".repeat(40);

    try (BufferedReader br = new BufferedReader(new FileReader(EXPORT_TYPE))) {
        String line;
        boolean inRecord = false;

        while ((line = br.readLine()) != null) {
            line = line.trim();

            if (line.equals("EXPORT RECEIPT")) {
                inRecord = true;
                exportCount++;
                System.out.println(separator);
                System.out.println(line);
                continue;
            }

            if (inRecord) {
                if (line.equals(separator)) {
                    inRecord = false;
                    System.out.println(separator);
                    System.out.println(); // blank line between records
                    continue;
                }
                System.out.println(line);
            }
        }

        if (exportCount == 0) {
            System.out.println("No export records found.");
        } else {
            System.out.println("Total number of exports: " + exportCount);
        }
    } catch (IOException e) {
        System.out.println("Error reading file: " + e.getMessage());
    }
}






//Method to generate quantity/value of exports in a period.
public void generateExportReport(Scanner input) {
    System.out.println("Enter start date (yyyy-MM-dd): ");
    String startStr = input.nextLine().trim();

    System.out.println("Enter end date (yyyy-MM-dd): ");
    String endStr = input.nextLine().trim();

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    LocalDate startDate, endDate;

    try {
        startDate = LocalDate.parse(startStr, formatter);
        endDate = LocalDate.parse(endStr, formatter);
    } catch (DateTimeParseException e) {
        System.out.println("Invalid date format. Please use yyyy-MM-dd.");
        return;
    }

    if (endDate.isBefore(startDate)) {
        System.out.println("End date cannot be before start date.");
        return;
    }

    int totalQuantity = 0;
    double totalValue = 0.0;
    String separator = "-".repeat(40);

    try (BufferedReader br = new BufferedReader(new FileReader(EXPORT_TYPE))) {
        String line;
        boolean inRecord = false;
        LocalDate exportDate = null;
        int quantity = 0;
        double unitPrice = 0.0;

        while ((line = br.readLine()) != null) {
            line = line.trim();

            if (line.equals("EXPORT RECEIPT")) {
                // Start of new record
                inRecord = true;
                exportDate = null; 
                quantity = 0;
                unitPrice = 0.0;
                continue;
            }

            if (inRecord) {
                if (line.startsWith("Export Date:")) {
                    String dateStr = line.substring("Export Date:".length()).trim();
                    try {
                        exportDate = LocalDate.parse(dateStr, formatter);
                    } catch (DateTimeParseException e) {
                        exportDate = null; // Invalid date in file, skip
                    }
                } else if (line.startsWith("Quantity:")) {
                    String qtyStr = line.substring("Quantity:".length()).trim();
                    try {
                        quantity = Integer.parseInt(qtyStr);
                    } catch (NumberFormatException e) {
                        quantity = 0;
                    }
                } else if (line.startsWith("Unit Price:")) {
                    String priceStr = line.substring("Unit Price:".length()).trim();
                    try {
                        unitPrice = Double.parseDouble(priceStr);
                    } catch (NumberFormatException e) {
                        unitPrice = 0.0;
                    }
                } else if (line.equals(separator)) {
                    // End of record
                    inRecord = false;

                    if (exportDate != null && 
                        ( !exportDate.isBefore(startDate) && !exportDate.isAfter(endDate) )) {
                        totalQuantity += quantity;
                        totalValue += quantity * unitPrice;
                    }
                }
            }
        }

        System.out.println(separator);
        System.out.println("Export Report from " + startDate + " to " + endDate);
        System.out.println("Total Quantity: " + totalQuantity);
        System.out.printf("Total Value: %.2f\n", totalValue);
        System.out.println(separator);

    } catch (IOException e) {
        System.out.println("Error reading file: " + e.getMessage());
    }
}



//CUSTOM EXCEPTION CLASSES
  public static class IncompleteNumberException extends Exception {
        public IncompleteNumberException(String message) {
            super(message);
        }

  
}
  //METHOD FOR ALL THE CUSTOM EXCEPTION CLASSES
    public void validatephoneNumber(String clientNumber) throws IncompleteNumberException{
        if( clientNumber == null || clientNumber.length() != 11){
        throw new IncompleteNumberException("Phone number is incomplete. It must be 11 digits");
        }
    }


}
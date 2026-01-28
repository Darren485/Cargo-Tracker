    import java.io.*;
    import java.util.*;

    public class ImportHandler {
        private String shipmentId;
        private String shipmentName;
        private String shipmentDate;
        private String countryOfOrigin;
        private int quantity;
        private double unitPrice;
        //The file we are writing to
        private static final String IMPORT_TYPE = "Import.txt";
        private String supplierName;
        private String clientName;

        //Constructor
        public ImportHandler(String shipmentId, String shipmentName, String shipmentDate, String countryOfOrigin, double unitPrice, int quantity,String supplierName, String clientName) {
            this.shipmentId = shipmentId;
            this.shipmentName = shipmentName;
            this.shipmentDate = shipmentDate;
            this.countryOfOrigin = countryOfOrigin;
            this.unitPrice = unitPrice;
            this.quantity = quantity;
            this.supplierName = supplierName;
            this.clientName = clientName;
        }


        //GET METHODS
        public String getShipmentId() {
            return shipmentId;
        }
        public String getShipmentName() {
            return shipmentName;
        }
        public String getShipmentDate() {
            return shipmentDate;
        }
        public String getCountryOfOrigin() {
            return countryOfOrigin;
        }
        public int getQuantity() {
            return quantity;
        }
        public double getUnitPrice() {
            return unitPrice;
        }
        public String getSupplierName() {
            return supplierName;
        }
        public String getClientName(){
            return clientName;
        }

        //SET METHODS
        public void setShipmentId(String shipmentId) {
            this.shipmentId = shipmentId;
        }
        public void setShipmentName(String shipmentName) {
            this.shipmentName = shipmentName;
        }
        public void setShipmentDate(String shipmentDate) {
            this.shipmentDate = shipmentDate;
        }
        public void setCountryOfOrigin(String countryOfOrigin) {
            this.countryOfOrigin = countryOfOrigin;
        }
        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }
        public void setUnitPrice(double unitPrice) {
            this.unitPrice = unitPrice;
        }
        public void setSupplierName(String supplierName) {
            this.supplierName = supplierName;
        }
        public void setClientName(String clientName){
            this.clientName = clientName;
        }



        //Method to calculate the total cost
        public double getTotalValue() {
            return quantity * unitPrice;
        }




        //Method to add a new import record
        public void recordImport(Scanner input){
            try(FileWriter fw = new FileWriter(IMPORT_TYPE, true);
            BufferedWriter bw = new BufferedWriter(fw)){
                
                System.out.println("Enter shipment ID: ");
                setShipmentId(input.nextLine().trim());

                System.out.println("Enter shipment name: ");
                setShipmentName(input.nextLine().trim());

                System.out.println("Enter shipment date: ");
                setShipmentDate(input.nextLine().trim());

                System.out.println("Enter country of origin: ");
                setCountryOfOrigin(input.nextLine().trim());

                System.out.println("Enter quantity: ");
                while(!input.hasNextInt()){
                    System.out.println("Invalid input. Please enter a valid quantity: ");
                    input.next();
                }
                setQuantity(input.nextInt());
                input.nextLine();
                //Tihis is to checkmate against empty input of input mismatch

                System.out.println("Enter unit price: ");
                    while(!input.hasNextDouble()){
                    System.out.println("Ivalid input. Please enter a valid price: ");
                    input.next();
                }            
                //Tihis is to checkmate against empty input of input mismatch

                input.nextLine();

                System.out.println("Enter supplier name: ");
                setSupplierName(input.nextLine().trim());
                //Here we are asking the user for input

                System.out.println("Input client name");
                setClientName(input.nextLine().trim());

                //Here we are writing to the text file
                String separator = "-".repeat(40);
                bw.write("SHIPMENT RECEIPT");
                bw.newLine();
                bw.write(separator);
                bw.newLine();
                bw.write("Shipment ID: " + getShipmentId());
                bw.newLine();
                bw.write("Product Name: " + getShipmentName());
                bw.newLine();
                bw.write("Shipment Date: " + getShipmentDate());
                bw.newLine();
                bw.write("Country of Origin: " + getCountryOfOrigin());
                bw.newLine();
                bw.write("Quantity: " + getQuantity());
                bw.newLine();
                bw.write("Unit Price: " +String.format("%.2f", getUnitPrice()));
                bw.newLine();
                bw.write("Total Cost: " + String.format("%.2f", getTotalValue()));
                bw.newLine();
                bw.write("Supplier Name: " + getSupplierName());
                bw.newLine();
                bw.write("Client Name" + getClientName());
                bw.newLine();
                bw.write(separator);
                bw.newLine();
                bw.newLine();
            } catch (IOException e) {
                System.out.println("Error writing to file: " + e.getMessage());
            }
        }




        //Method to view all shipments
        public void viewImports(){
            int count = 0;

            try (FileReader fr = new FileReader(IMPORT_TYPE);
                BufferedReader br = new BufferedReader(fr)) {

                String line;
                System.out.println("\n📦 === Warehouse Imports ===\n");
                String separator = "-".repeat(40);
                boolean inRecord = false;

                while ((line = br.readLine()) != null) {
                    line = line.trim();

                    if(line.equals("SHIPMENT RECEIPT")){
                        inRecord = true;
                        count++;
                        System.out.println(separator);  
                        System.out.println(line);
                        continue;
                    }

                    if(inRecord){
                        if(line.equals(separator)){
                            inRecord = false;
                            System.out.println(separator);
                            System.out.println();
                            continue;
                        }

                        System.out.println(line);
                    }
                    
                    }

                System.out.println("Total Imports: " + count);
                }catch (IOException e) {
                System.out.println("Error reading file: " + e.getMessage());
            }
        }




        //Method to see all products received from a supplier.
    public void checkSupplierHistory(Scanner input) {
        System.out.println("Enter supplier name:");
        String supplierNameToFind = input.nextLine().trim();

        boolean found = false;
        boolean inRecord = false;
        boolean printRecord = false;
        String separator = "-".repeat(40);

        try (BufferedReader br = new BufferedReader(new FileReader(IMPORT_TYPE))) {
            String line;

            while ((line = br.readLine()) != null) {
                line = line.trim();

                if (line.equals("SHIPMENT RECEIPT")) {
                    // Start of a new shipment record
                    inRecord = true;
                    printRecord = false;  // reset for this record
                    // Temporarily store record lines to print if supplier matches
                    StringBuilder recordBuffer = new StringBuilder();
                    recordBuffer.append(line).append("\n");
                    
                    // Read lines within the shipment receipt
                    while ((line = br.readLine()) != null && !line.trim().equals(separator)) {
                        recordBuffer.append(line).append("\n");
                        if (line.startsWith("Supplier Name:")) {
                            String currentSupplier = line.substring("Supplier Name:".length()).trim();
                            if (currentSupplier.equalsIgnoreCase(supplierNameToFind)) {
                                printRecord = true;
                                found = true;
                            }
                        }
                    }
                    recordBuffer.append(separator).append("\n");

                    // If matched supplier, print the full record
                    if (printRecord) {
                        System.out.println(separator);
                        System.out.print(recordBuffer.toString());
                        System.out.println(separator);
                        System.out.println();
                    }
                }
            }

            if (!found) {
                System.out.println("Supplier not found.");
            }

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }


    }
                
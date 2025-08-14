import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        ExportHandler exportHandler = new ExportHandler("", "", "", "", 0, 0.0, "", "", "");
        ImportHandler importHandler = new ImportHandler("", "", "", "", 0.0, 0, "", "");
        Client client = null; // We'll create a client object when needed

        boolean running = true;

        while (running) {
            System.out.println("\n--- Warehouse System Menu ---");
            System.out.println("1. Record Export");
            System.out.println("2. View All Exports");
            System.out.println("3. Export Report");
            System.out.println("4. Record Import");
            System.out.println("5. View All Imports");
            System.out.println("6. Check Import Supplier History");
            System.out.println("7. Add Client");
            System.out.println("8. View Client Info");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");

            String choice = input.nextLine().trim();

            switch (choice) {
                case "1":
                    exportHandler.recordExport(input);
                    break;
                case "2":
                    exportHandler.viewAllExports();
                    break;
                case "3":
                    exportHandler.generateExportReport(input);
                    break;
                case "4":
                    importHandler.recordImport(input);
                    break;
                case "5":
                    importHandler.viewImports();
                    break;
                case "6":
                    importHandler.checkSupplierHistory(input);
                    break;
                case "7":
                    System.out.print("Enter client name: ");
                    String clientName = input.nextLine().trim();
                    System.out.print("Enter client phone number: ");
                    String clientPhone = input.nextLine().trim();
                    client = new Client(clientName, clientPhone);
                    System.out.println("Client added.");
                    break;
                case "8":
                    if (client != null) {
                        System.out.println(client);
                    } else {
                        System.out.println("No client info available. Please add a client first.");
                    }
                    break;
                case "0":
                    System.out.println("Exiting...");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option, try again.");
            }
        }

        input.close();
    }

    // Simple Client class nested here
    static class Client {
        private String name;
        private String phoneNumber;

        public Client(String name, String phoneNumber) {
            this.name = name;
            this.phoneNumber = phoneNumber;
        }

        public String getName() { return name; }
        public String getPhoneNumber() { return phoneNumber; }

        @Override
        public String toString() {
            return "Client Name: " + name + ", Phone: " + phoneNumber;
        }
    }
}

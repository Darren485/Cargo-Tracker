# Cargo Tracker

## Overview
Cargo Tracker is a Java console-based application designed to manage import and export cargo records.  
It allows users to store, view, and calculate cargo shipment details such as product name, arrival date, origin country, quantity, and total value.  

The project is built to practice Java programming concepts including:
- Classes & Objects
- Arrays
- Loops
- String Manipulation
- Console I/O (Input/Output)

---

## Features
- Add new import and export records
- View all stored cargo records in a tabular format
- Automatically calculate the total value of cargo based on quantity and price
- User-friendly console interface

---

## How It Works
1. **Main Menu**:  
   The user selects between managing **Imports**, **Exports**, or **Client Details**.
   
2. **Adding Records**:  
   The program prompts for shipment details:
   - Shipment ID
   - Product Name
   - Arrival Date
   - Country of Origin
   - Quantity
   - Unit Price  
   It then calculates `Total Value = Quantity × Unit Price`.

3. **Viewing Records**:  
   Data is displayed in a formatted table.

---

## Example Output

ID | Product   | Arrival Date | Country  | Qty | Price  | Total Value  
001 | Cocoa     | 2025-08-14   | Ghana    | 50  | 100.0  | 5000.0  
002 | Cashew    | 2025-08-14   | Nigeria  | 30  | 150.0  | 4500.0  

---

## Requirements
- **Java Development Kit (JDK)** 8 or later
- Any Java IDE (IntelliJ IDEA, Eclipse, NetBeans) or plain text editor with terminal access

---

## Running the Program
1. Save all `.java` files in the same directory.
2. Open your terminal or command prompt in that directory.
3. Compile:

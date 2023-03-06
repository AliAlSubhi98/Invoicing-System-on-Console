package src;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class Main {
	static Scanner scanner = new Scanner(System.in);
	static Shop shop = new Shop();

	public static void main(String[] args) {

		while (true) {
			System.out.println("Main Menu:");
			System.out.println("1- Shop Settings");
			System.out.println("2- Manage Shop Items");
			System.out.println("3- Create new Invoice");
			System.out.println("4- Report: Statistics (No Of Items, No of Invoices, Total Sales)");
			System.out.println("5- Report: All Invoices ( Invoice No, Invoice Date, Customer Name, No of items, Total, Balance) ");
			System.out.println("6- Search (1) Invoice (Search by Invoice No and Report All Invoice details with items)");
			System.out.println("7- Program Statistics (Print each Main Menu Item with how many time it has been  selected).");
			System.out.println("8- Exit");

			int choice = scanner.nextInt();
			scanner.nextLine(); // consume the newline character

			switch (choice) {
			case 1:
				System.out.println("Shop Settings:");
				System.out.println("1- Load Data (Items and invoices)");
				System.out.println("2- Set Shop Name (data should be saved)");
				System.out.println("3- Set Invoice Header (Tel / Fax / Email / Website) (Data should be saved)");
				System.out.println("4- Go Back");

				int shopChoice = scanner.nextInt();

				switch (shopChoice) {
				case 1:
					// load data
					break;
				case 2:
					System.out.println("Enter new shop name:");
					String newName = scanner.next();
					shop.setShopName(newName);
					// save data
					break;
				case 3:// Set Invoice Header (Tel / Fax / Email / Website) (Data should be saved
					shop.setInvoiceHeader();
					System.out.println(shop.toString());
					break;
				// save data
				case 4:
					// shop.loadInvoiceHeader(); // work
					// go back
					break;
				default:
					System.out.println("Invalid choice.");
					break;
				}
				break;
			case 2:
				System.out.println("Manage Shop Items:");
				System.out.println("1- Add Items (Item should be saved/serialized)");
				System.out.println("2- Delete Items");
				System.out.println("3- Change Item Price");
				System.out.println("4- Report All Items");
				System.out.println("5- Go Back");

				int itemChoice = scanner.nextInt();

				switch (itemChoice) {
				case 1:
					// add item
					addItem();
					break;
				case 2:
					// delete item
					removeItem();
					break;
				case 3:
					// change item price
					changePrice();
					break;
				case 4:
					// report all items
					reportAllItems();
					downloadAllReportItems();
					break;
				case 5:
					// go back
					break;
				// case 6: // test methods
				// loadItems();
				// System.out.println(loadItems());
				// printItems();
				// removeItem();
				// break;
				default:
					System.out.println("Invalid choice.");
					break;
				}
				break;
			case 3: //3- Create new Invoice"
				Invoice invoice = createInvoice(shop.items);
				if (invoice != null) {
					invoice.printInvoice();
				}
				downloadInvoice(invoice);
				break;
			case 4:
				generateStatisticsReport();
				break;
			case 5:
				break;
			case 6:
				break;
			case 7:
				break;
			case 8:
				System.out.println("Exiting program...");
				return;
			default:
				System.out.println("Invalid choice.");
				break;
			}
		}
	}
	// ----------------------------------------------------------------------

	/*@SuppressWarnings("unchecked") // suppress unchecked cast warning
	public static ArrayList<Item> loadItems() {
		ArrayList<Item> items = null;
		try {
			FileInputStream fis = new FileInputStream("items.ser");
			ObjectInputStream ois = new ObjectInputStream(fis);
			Object obj = ois.readObject();
			if (obj instanceof ArrayList) {
				items = (ArrayList<Item>) obj; // type-safe cast
			}
			ois.close();
			fis.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (ClassNotFoundException cne) {
			cne.printStackTrace();
		}
		return items;
	}*/
	// ----------------------------------------------------------------------

	/*private static void printItems() {
		try {
			FileInputStream fis = new FileInputStream("items.ser");
			ObjectInputStream ois = new ObjectInputStream(fis);
			ArrayList<Item> items = (ArrayList<Item>) ois.readObject();
			ois.close();
			fis.close();
			for (Item item : shop.items) {
				System.out.println(item);
			}
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}*/
	// ----------------------------------------------------------------------

	private static void removeItem() {
		System.out.println("Enter item ID to remove:");
		int idToRemove = scanner.nextInt();
		boolean found = false;
		for (int i = 0; i < shop.items.size(); i++) {
			if (shop.items.get(i).getId() == idToRemove) {
				shop.items.remove(i);
				found = true;
				break;
			}
		}
		if (found) {
			System.out.println("Item removed successfully.");
		} else {
			System.out.println("Item with ID " + idToRemove + " not found.");
		}
		try {
			FileOutputStream fos = new FileOutputStream("items.ser");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(shop.items);
			oos.close();
			fos.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
	// ----------------------------------------------------------------------

	private static void changePrice() {
		System.out.println("Enter item ID to change price:");
		int itemId = scanner.nextInt();

		boolean itemFound = false;
		for (Item item : shop.items) {
			if (item.getId() == itemId) {
				System.out.println("Enter new unit price:");
				double newPrice = scanner.nextDouble();
				item.setUnitPrice(newPrice);
				itemFound = true;
				break;
			}
		}

		if (itemFound) {
			try {
				FileOutputStream fos = new FileOutputStream("items.ser");
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				oos.writeObject(shop.items);
				oos.close();
				fos.close();
				System.out.println("Item price changed successfully.");
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		} else {
			System.out.println("Item not found.");
		}
	}
	// ----------------------------------------------------------------------

	private static void reportAllItems() {
		try {
			FileInputStream fis = new FileInputStream("items.ser");
			ObjectInputStream ois = new ObjectInputStream(fis);
			ArrayList<Item> items = (ArrayList<Item>) ois.readObject();
			ois.close();
			fis.close();
			System.out.println("All Items Report:");
			System.out.println("-----------------------");
			for (Item item : shop.items) {
				System.out.println(item.toString());
			}
			System.out.println("-----------------------");
		} catch (IOException | ClassNotFoundException ioe) {
			ioe.printStackTrace();
		}
	}
	private static void downloadAllReportItems() {
	    try {
	        FileInputStream fis = new FileInputStream("items.ser");
	        ObjectInputStream ois = new ObjectInputStream(fis);
	        List<Item> items = (List<Item>) ois.readObject();
	        ois.close();
	        fis.close();
		    File folder = new File("C:\\Users\\Lenovo\\eclipse-workspace\\groceries_shop\\ReportAllItems");
	        if (!folder.exists()) { // check if the folder not exist
	            folder.mkdir(); // if not - create folder
	        }
	        File myFile = new File(folder, "all_items_report.txt");
	        FileWriter fw = new FileWriter(myFile, true);
	        Date currentDate = new Date();
	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	        fw.write("Date: " + dateFormat.format(currentDate) + "\n");
	        fw.write("All Items Report:\n");
	        fw.write("-----------------------\n");
	        for (Item item : items) {
	            fw.write(item.toString() + "\n");
	        }
	        fw.write("-----------------------\n");
	        fw.close();
	        System.out.println("Report downloaded successfully.");
	    } catch (IOException | ClassNotFoundException ioe) {
	        ioe.printStackTrace();
	    }
	}




	// ----------------------------------------------------------------------
	public static Invoice createInvoice(List<Item> items2) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter customer name: ");
		String customerName = scanner.nextLine();
		System.out.println("Enter customer phone number: ");
		String customerPhoneNumber = scanner.nextLine();
		System.out.println("Enter invoice date (yyyy/MM/dd): ");
		String invoiceDateString = scanner.nextLine();
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date invoiceDate = null;
		try {
			invoiceDate = dateFormat.parse(invoiceDateString);
		} catch (ParseException e) {
			System.out.println("Invalid date format");
			return null;
		}
		System.out.println("Choose items from the list below: ");
		for (Item item : items2) {
			System.out.println(item.toString());
		}
		System.out.println("Enter number of items: ");
		int numberOfItems = scanner.nextInt();
		ArrayList<Item> items = new ArrayList<Item>();
		for (int i = 1; i <= numberOfItems; i++) {
			System.out.println("Enter item " + i + " id: ");
			int itemId = scanner.nextInt();
			Item chosenItem = null;
			for (Item item : items2) {
				if (item.getId() == itemId) {
					chosenItem = item;
					break;
				}
			}
			if (chosenItem == null) {
				System.out.println("Invalid item id");
				return null;
			}
			System.out.println("Enter item " + i + " quantity: ");
			int quantity = scanner.nextInt();
			chosenItem.setQuantity(chosenItem.getQuantity()-quantity);
			chosenItem.setQty(chosenItem.getQuantity()*chosenItem.getUnitPrice());
			items.add(chosenItem);
		}
		double totalAmount = 0;
		for (Item item : items) {
			totalAmount += item.getTotalPrice();
		}
		System.out.println("Enter paid amount: ");
		double paidAmount = scanner.nextDouble();
		double balance = paidAmount - totalAmount;
		int id = (int) (new Date().getTime() / 1000); // time in milliSecond
		Invoice invoice = new Invoice(id, customerName, customerPhoneNumber, invoiceDate, items, totalAmount,
				paidAmount, balance);
		if (shop.invoices == null) {
			shop.invoices = new ArrayList<Invoice>();
		}
		shop.invoices.add(invoice);
		try {
			FileOutputStream fos = new FileOutputStream("invoice.ser");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(shop.invoices);
			oos.close();
			fos.close();
			System.out.println("Invoice added successfully.");
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		return invoice;
	}

//----------------------------------------------------------------------
	private static void addItem() {
		System.out.println("Enter item ID:");
		int id = scanner.nextInt();
		System.out.println("Enter item name:");
		String name = scanner.next();
		System.out.println("Enter item unit price:");
		double unitPrice = scanner.nextDouble();
		System.out.println("Enter item quantity:");
		int quantity = scanner.nextInt();
		double qty = unitPrice * quantity;
		Item item = new Item(id, name, unitPrice, quantity,qty);
		shop.items.add(item);

		try {
			FileOutputStream fos = new FileOutputStream("items.ser");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(shop.items);
			oos.close();
			fos.close();
			System.out.println("Item added successfully.");
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	// ----------------------------------------------------------------------
	/*static void downloadInvoice(Invoice invoice) {
	    File folder = new File("C:\\Users\\Lenovo\\Documents\\Invoices");
	    if (!folder.exists()) { // check if the folder not exist
	        folder.mkdir(); // if not - create folder
	    }
	    String fileName = String.format("Invoice_%d.txt", invoice.getId());
	    File myFile = new File(folder, fileName);
	    try {
	        FileWriter fw = new FileWriter(myFile, true);
	        //header
	        fw.write("Shop name: " + shop.getShopName());
	        fw.write("Tel: " + shop.getTel());
	        fw.write("Fax: " + shop.getFax());
	        fw.write("Email: " + shop.getEmail());
	        fw.write("Website: " + shop.getWebsite());

	        fw.write("Invoice #" + invoice.getId() + "\n");
	        fw.write("Customer name: " + invoice.getCustomerName() + "\n");
	        fw.write("Customer phone number: " + invoice.getPhoneNumber() + "\n");
	        fw.write("Invoice date: " + invoice.getInvoiceDate() + "\n");
	        fw.write("--------------------------------------------------------------\n");
	        fw.write(String.format("%-20s%-20s%-20s%-20s\n", "Item name", "Unit price", "Quantity", "Total price"));
	        fw.write("--------------------------------------------------------------\n");
	        for (Item item : invoice.getItems()) {
	            fw.write(String.format("%-20s%-20.2f%-20d%-20.2f\n", item.getName(), item.getUnitPrice(), item.getQuantity(), item.getTotalPrice()));
	        }
	        fw.write("--------------------------------------------------------------\n");
	        fw.write(String.format("%60s%-20.2f\n", "Total amount: ", invoice.getTotalAmount()));
	        fw.write(String.format("%60s%-20.2f\n", "Paid amount: ", invoice.getPaidAmount()));
	        fw.write(String.format("%60s%-20.2f\n", "Balance: ", invoice.getBalance()));
	        fw.close();
	        System.out.println("Invoice downloaded successfully.");
	    } catch (IOException e) {
	        System.out.println("Error in FileWriter");
	        e.printStackTrace();
	    }
	}*/
	
	/*static void downloadInvoice(Invoice invoice) {
		
	    File folder = new File("C:\\Users\\Lenovo\\eclipse-workspace\\groceries_shop\\Invoices");
	    if (!folder.exists()) { // check if the folder not exist
	        folder.mkdir(); // if not - create folder
	    }
	    String fileName = String.format("Invoice_%d.txt", invoice.getId());
	    File myFile = new File(folder, fileName);
	    try {
	        FileWriter fw = new FileWriter(myFile, true);
	        // header
	        fw.write(String.format("%50s\n\n", shop.getShopName()));
	        fw.write(String.format("%52s\n\n", "INVOICE"));
	        fw.write(String.format("%50s\n", shop.getFax()));
	        fw.write(String.format("%50s\n", shop.getTel()));
	        fw.write(String.format("%50s\n", shop.getEmail()));
	        fw.write(String.format("%50s\n\n", shop.getWebsite()));

	        fw.write(String.format("%-30s%-20s%-20s%-20s%-20s\n", "ITEM", "UNIT PRICE", "QUANTITY", "AMOUNT","QTY"));
	        fw.write(String.format("%-30s%-20s%-20s%-20s\n", "----", "----------", "--------", "------", "---"));
	        for (Item item : invoice.getItems()) {
	            fw.write(String.format("%-30s%-20.2f%-20d%-20.2f\n", item.getName(), item.getUnitPrice(), item.getQuantity(), item.getTotalPrice(), item.getQty()));
	        }
	        fw.write(String.format("%80s\n", "-----------"));
	        fw.write(String.format("%60s%-20.2f\n", "SUBTOTAL: ", invoice.getTotalAmount()));
	        fw.write(String.format("%60s%-20.2f\n", "PAID: ", invoice.getPaidAmount()));
	        fw.write(String.format("%60s%-20.2f\n\n", "BALANCE: ", invoice.getBalance()));

	        fw.write(String.format("%50s\n", "Thank you for your business!"));
	        fw.write(String.format("%50s\n\n", "Please come again."));

	        fw.write(String.format("%50s\n", "Invoice Number: " + invoice.getId()));
	        fw.write(String.format("%50s\n", "Customer Name: " + invoice.getCustomerName()));
	        fw.write(String.format("%50s\n", "Phone Number: " + invoice.getPhoneNumber()));
	        fw.write(String.format("%50s\n", "Invoice Date: " + invoice.getInvoiceDate()));

	        fw.close();
	        System.out.println("Invoice downloaded successfully.");
	    } catch (IOException e) {
	        System.out.println("Error in FileWriter");
	        e.printStackTrace();
	    }
	}
*/

// ----------------------------------------------------------------------
	static void downloadInvoice(Invoice invoice) {
	    File folder = new File("C:\\Users\\Lenovo\\Documents\\Invoices");
	    if (!folder.exists()) { // check if the folder not exist
	        folder.mkdir(); // if not - create folder
	    }
	    String fileName = String.format("Invoice_%d.txt", invoice.getId());
	    File myFile = new File(folder, fileName);
	    try {
	        FileWriter fw = new FileWriter(myFile, true);
	        // Header
	        fw.write("+------------------------------------------------------------+\n");
	        fw.write(String.format("| %-28s%30s |\n", "Shop name:", shop.getShopName()));
	        fw.write(String.format("| %-28s%30s |\n", "Tel:", shop.getTel()));
	        fw.write(String.format("| %-28s%30s |\n", "Fax:", shop.getFax()));
	        fw.write(String.format("| %-28s%30s |\n", "Email:", shop.getEmail()));
	        fw.write(String.format("| %-28s%30s |\n", "Website:", shop.getWebsite()));
	        fw.write("+------------------------------------------------------------+\n");
	        fw.write(String.format("| %-60s |\n", "Invoice #" + invoice.getId()));
	        fw.write(String.format("| %-60s |\n", "Customer name: " + invoice.getCustomerName()));
	        fw.write(String.format("| %-60s |\n", "Customer phone number: " + invoice.getPhoneNumber()));
	        fw.write(String.format("| %-60s |\n", "Invoice date: " + invoice.getInvoiceDate()));
	        fw.write("+------------------------------------------------------------+\n");
	        fw.write(String.format("| %-20s| %-20s| %-20s| %-20s|\n", "Item name", "Unit price", "Quantity", "Total price"));
	        fw.write("+------------------------------------------------------------+\n");
	        for (Item item : invoice.getItems()) {
	            fw.write(String.format("| %-20s| %-20.2f| %-20d| %-20.2f|\n", item.getName(), item.getUnitPrice(), item.getQuantity(), item.getTotalPrice()));
	        }
	        fw.write("+------------------------------------------------------------+\n");
	        fw.write(String.format("| %-60s| %-20.2f |\n", "Total amount:", invoice.getTotalAmount()));
	        fw.write(String.format("| %-60s| %-20.2f |\n", "Paid amount:", invoice.getPaidAmount()));
	        fw.write(String.format("| %-60s| %-20.2f |\n", "Balance:", invoice.getBalance()));
	        fw.write("+------------------------------------------------------------+\n");
	        fw.close();
	        System.out.println("Invoice downloaded successfully.");
	    } catch (IOException e) {
	        System.out.println("Error in FileWriter");
	        e.printStackTrace();
	    }
	}
	public static void generateStatisticsReport() {
	    int numItems = shop.getItems().size();
	    int numInvoices = shop.getInvoices().size();
	    double totalSales = 0.0;
	    for (Invoice invoice : shop.getInvoices()) {
	        totalSales += invoice.getTotalAmount();
	    }
	    System.out.println("Statistics Report:");
	    System.out.println("Number of Items: " + numItems);
	    System.out.println("Number of Invoices: " + numInvoices);
	    System.out.println("Total Sales: $" + String.format("%.2f", totalSales));
	}


}

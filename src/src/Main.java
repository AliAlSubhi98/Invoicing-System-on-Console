package src;

import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.io.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class Main {
	static Scanner scanner = new Scanner(System.in);
	static int enterShopSettingsCounter = 0;
	static int enterManageShopItemsCounter = 0;
	static int enterCreatenewInvoiceCounter = 0;
	static int enterReportStatisticsCounter = 0;
	static int enterReportAllInvoicesCounter = 0;
	static int enterSearchInvoiceCounter = 0;
	static int enterProgramStatisticsCounter = 0;
	static int enterCounter = 0;

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
				enterShopSettingsCounter++;
				System.out.println("Shop Settings:");
				System.out.println("1- Load Data (Items and invoices)");
				System.out.println("2- Set Shop Name (data should be saved)");
				System.out.println("3- Set Invoice Header (Tel / Fax / Email / Website) (Data should be saved)");
				System.out.println("4- Go Back");

				int shopChoice = scanner.nextInt();

				switch (shopChoice) {
				case 1:
					// load data
					shop.deserializeShopName("shop.ser");
					deserializeItems("items.ser");
					Shop.loadInvoiceHeader();
					break;
				case 2:
					// System.out.println("Enter new shop name:");
					// String newName = scanner.next();
					// shop.setShopName(newName);
					shop.setShopNameAndSerialize();
					// save data
					break;
				case 3:// Set Invoice Header (Tel / Fax / Email / Website) (Data should be saved
					shop.setInvoiceHeader();
					System.out.println(shop.toString());
					break;
				// save data
				case 4:
					// go back
					break;
				default:
					System.out.println("Invalid choice.");
					break;
				}
				break;
			case 2:
				enterManageShopItemsCounter++;
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
					saveItemsSerialized(shop.items);
					ArrayList<Item> items = deserializeItems("items.ser");
					if (items != null) {
						for (Item item : items) {
							System.out.println(item.toString());
						}
					} else {
						System.out.println("Failed to deserialize items.");
					}
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
					downloadAllReportItems(shop.items);
					break;
				case 5:
					// go back
					break;
				default:
					System.out.println("Invalid choice.");
					break;
				}
				break;
			case 3: // 3- Create new Invoice"
				enterCreatenewInvoiceCounter++;
				Invoice invoice = createInvoice(shop.items);
				if (invoice != null) {
					invoice.printInvoice();
				}
				downloadInvoice(invoice);
				break;
			case 4:
				enterReportStatisticsCounter++;
				generateStatisticsReport();
				break;
			case 5:
				enterReportAllInvoicesCounter++;
				 generateInvoiceReport();
				break;
			case 6:
				enterSearchInvoiceCounter++;
				searchInvoice();
				break;
			case 7:
				enterProgramStatisticsCounter++;
				systemStatistics();
				break;
			case 8:
				//CreatingTables.truncatesItems();
				//CreatingTables.createTablesInDataBase();
				//CreatingTables.insertIntoItems();
				CreatingTables.truncatesInvoices();
				CreatingTables.truncatesShop();
				System.out.println("Exiting program...");
				//return;
			default:
				System.out.println("Invalid choice.");
				break;
			}
		}
	}
	// ----------------------------------------------------------------------
	private static void systemStatistics() {
		System.out.println("Main Menu Statistics:");
		System.out.println("1- Shop Settings" + enterShopSettingsCounter);
		System.out.println("2- Manage Shop Items" + enterManageShopItemsCounter);
		System.out.println("3- Create new Invoice" + enterCreatenewInvoiceCounter);
		System.out.println(
				"4- Report: Statistics (No Of Items, No of Invoices, Total Sales)" + enterReportStatisticsCounter);
		System.out.println(
				"5- Report: All Invoices ( Invoice No, Invoice Date, Customer Name, No of items, Total, Balance) "
						+ enterReportAllInvoicesCounter);
		System.out.println("6- Search (1) Invoice (Search by Invoice No and Report All Invoice details with items)"
				+ enterSearchInvoiceCounter);
		System.out.println("7- Program Statistics (Print each Main Menu Item with how many time it has been  selected)."
				+ enterProgramStatisticsCounter);
		System.out.println("-----------------------------------------------------------------------------------------");
		System.out.println("Shop Settings Statistics:");
		System.out.println("1- Load Data (Items and invoices)");
		System.out.println("2- Set Shop Name (data should be saved)");
		System.out.println("3- Set Invoice Header (Tel / Fax / Email / Website) (Data should be saved)");
		System.out.println("4- Go Back");
		System.out.println("-----------------------------------------------------------------------------------------");
		System.out.println("Manage Shop Items Statistics:");
		System.out.println("1- Add Items (Item should be saved/serialized)");
		System.out.println("2- Delete Items");
		System.out.println("3- Change Item Price");
		System.out.println("4- Report All Items");
		System.out.println("5- Go Back");
	}
	// ----------------------------------------------------------------------
	static void removeItem() {
		try {
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
			FileOutputStream fos = new FileOutputStream("items.ser");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(shop.items);
			oos.close();
			fos.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (InputMismatchException ime) {
			System.out.println("Invalid input. Please enter a valid integer ID.");
			scanner.nextLine(); // clear scanner buffer
		} catch (Exception e) {
			e.printStackTrace();
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
		System.out.println("All Items Report:");
		System.out.println("-----------------------");
		for (Item item : shop.items) {
			System.out.println(item.toString());
			System.out.println("-----------------------");
		}
	}
	// ----------------------------------------------------------------------
	private static void downloadAllReportItems(ArrayList<Item> items) {
	    try {
	        File folder = new File("ReportOfItems");
	        if (!folder.exists()) {
	            folder.mkdirs(); // create the directory and any missing parent directories
	        }

	        File myFile = new File(folder, "all_items_report.txt");
	        FileWriter fw = new FileWriter(myFile, true);

	        Date currentDate = new Date();
	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	        fw.write("Date: " + dateFormat.format(currentDate) + "\n\n");
	        fw.write("All Items Report:\n\n");
	        fw.write(String.format("%-5s%-20s%-10s\n", "ID", "Name", "Unit Price"));
	        fw.write("----------------------------------------------------\n");
	        for (Item item : items) {
	            fw.write(String.format("%-5d%-20s%-10.2f\n", item.getId(), item.getName(), item.getUnitPrice()));
	        }
	        fw.write("----------------------------------------------------\n\n");
	        fw.close();
	        System.out.println("Report downloaded successfully.");
	    } catch (IOException ioe) {
	        ioe.printStackTrace();
	    }
	}
	// ----------------------------------------------------------------------
	private static void saveItemsSerialized(ArrayList<Item> items) {
	    try {
	        FileOutputStream fos = new FileOutputStream("items.ser");
	        ObjectOutputStream oos = new ObjectOutputStream(fos);
	        oos.writeObject(items);
	        oos.close();
	        fos.close();
	        System.out.println("Items saved(serialized) successfully.");
	    } catch (IOException ioe) {
	        ioe.printStackTrace();
	    }
	}
	// ----------------------------------------------------------------------
	public static Invoice createInvoice(List<Item> items2) {
		System.out.println("Enter customer name: ");
		String customerName = scanner.nextLine();
		System.out.println("Enter customer phone number: ");
		String customerPhoneNumber = scanner.nextLine();
		LocalDate invoiceDate = LocalDate.now();
		System.out.println("Choose items from the list below: ");
		System.out.println(String.format("%-5s%-20s%-10s", "ID", "Name", "Unit Price"));
		for (Item item : items2) {
			System.out.println(item.idNameUnitprice());
		}
		ArrayList<Item> items = new ArrayList<Item>();
		while (true) {
			System.out.println("Enter item ID to add or type 'done' to finish: ");
			String input = scanner.nextLine();
			if (input.equals("done")) {
				break;
			}
			try {
				int itemId = Integer.parseInt(input);
				Item chosenItem = null;
				for (Item item : items2) {
					if (item.getId() == itemId) {
						chosenItem = item;
						break;
					}
				}
				if (chosenItem == null) {
					System.out.println("Invalid item ID.");
					continue;
				}
				System.out.println("Enter quantity for item " + chosenItem.getName() + ":");
				int quantity = scanner.nextInt();
				scanner.nextLine(); // consume the newline character
				chosenItem.setQuantity(quantity);
				chosenItem.setQty(chosenItem.getQuantity() * chosenItem.getUnitPrice());
				items.add(chosenItem);
			} catch (NumberFormatException e) {
				System.out.println("Invalid input. Please enter a valid item ID or 'done' to finish.");
			}
		}
		double totalAmount = 0;
		for (Item item : items) {
			totalAmount += item.getTotalPrice();
		}
		System.out.println("Total Amount of items chosen = " + totalAmount);
		System.out.println("Enter paid amount: ");
		double paidAmount = 0;
		try {
			paidAmount = scanner.nextDouble();
		} catch (InputMismatchException e) {
			System.out.println("Invalid input. Please enter a valid number for paid amount.");
			return null;
		}
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
			System.out.println("Invoice added(serialized) successfully.");
		} catch (IOException ioe) {
			System.out.println("Error saving invoice. Please try again.");
			ioe.printStackTrace();
		}
		return invoice;
	}
	// ----------------------------------------------------------------------
	private static void addItem() {
		try {
			System.out.println("Enter item ID:");
			int id = scanner.nextInt();
			scanner.nextLine();
			System.out.println("Enter item name:");
			String name = scanner.nextLine();
			System.out.println("Enter item unit price:");
			double unitPrice = scanner.nextDouble();
			scanner.nextLine();

			Item item = new Item(id, name, unitPrice);
			shop.items.add(item);

			try {
				FileOutputStream fos = new FileOutputStream("items.txt");
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				oos.writeObject(shop.items);
				oos.close();
				fos.close();
				System.out.println("Item added successfully.");
			} catch (IOException ioe) {
				System.out.println("Error writing to file.");
			}
		} catch (InputMismatchException e) {
			System.out.println("Invalid input. Please enter a valid number for item ID or unit price.");
			scanner.nextLine(); // consume the invalid input
		}
	}
	// ----------------------------------------------------------------------
	static void downloadInvoice(Invoice invoice) {
		File folder = new File("Invoices");
		//File folder = new File("C:\\Users\\BlackDell\\git\\Invoicing-System-on-Console\\Invoices");

		if (!folder.exists()) {
			folder.mkdir();
		}
		String fileName = String.format("Invoice_%d.txt", invoice.getId());
		File myFile = new File(folder, fileName);
		try {
			FileWriter fw = new FileWriter(myFile, true);
			// Header
			fw.write("+---------------------------------------------------------------------------------------+\n");
			fw.write(String.format("| %-20s | %-20s | %-20s | %-20s |\n", "Shop name:", shop.getShopName(), "Tel:",
					shop.getTel()));
			fw.write(String.format("| %-20s | %-20s | %-20s | %-20s |\n", "Fax:", shop.getFax(), "Email:",
					shop.getEmail()));
			fw.write(String.format("| %-20s | %-20s | %-20s | %-20s |\n", "Website:", shop.getWebsite(), "", ""));
			fw.write("+---------------------------------------------------------------------------------------+\n");

			fw.write(String.format("| %-60s |\n", "Invoice #" + invoice.getId()));
			fw.write(String.format("| %-60s |\n", "Customer name: " + invoice.getCustomerName()));
			fw.write(String.format("| %-60s |\n", "Customer phone number: " + invoice.getPhoneNumber()));
			fw.write(String.format("| %-60s |\n", "Invoice date: " + invoice.getInvoiceDate()));
			fw.write("+---------------------------------------------------------------------------------------+\n");
			fw.write(String.format("| %-20s | %-20s | %-20s | %-20s |\n", "Item name", "Unit price", "Quantity",
					"Total price"));
			fw.write("+---------------------------------------------------------------------------------------+\n");
			for (Item item : invoice.getItems()) {
				fw.write(String.format("| %-20s | OMR%-19.2f | %-19d | OMR%-19.2f |\n", item.getName(), item.getUnitPrice(),
						item.getQuantity(), item.getTotalPrice()));
			}
			fw.write("+---------------------------------------------------------------------------------------+\n");
			fw.write(String.format("| %-60s | OMR%-19.2f |\n", "Total amount:", invoice.getTotalAmount()));
			fw.write(String.format("| %-60s | OMR%-19.2f |\n", "Paid amount:", invoice.getPaidAmount()));
			fw.write(String.format("| %-60s | OMR%-19.2f |\n", "Balance:", invoice.getBalance()));
			fw.write("+---------------------------------------------------------------------------------------+\n");

			fw.close();
			System.out.println("Invoice downloaded successfully.");
		} catch (IOException e) {
			System.out.println("Error in FileWriter");
			e.printStackTrace();
		}
	}
	// ----------------------------------------------------------------------
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
		System.out.println("Total Sales: OMR" + String.format("%.2f", totalSales));
	}
	// ----------------------------------------------------------------------
	public static void generateInvoiceReport() {
		List<Invoice> invoices = shop.getInvoices();
		if (invoices == null || invoices.isEmpty()) {
			System.out.println("No invoices found.");
			return;
		}

		System.out.println("Invoice Report:\n");
		System.out.printf("%-15s%-15s%-25s%-15s%-15s%-15s\n", "Invoice No.", "Invoice Date", "Customer Name",
				"No of Items", "Total", "Balance");
		System.out.println(
				"---------------------------------------------------------------------------------------------------");

		for (Invoice invoice : invoices) {
			int invoiceNo = invoice.getId();
			String invoiceDate = invoice.getInvoiceDate().toString();
			String customerName = invoice.getCustomerName();
			int numOfItems = invoice.getItems().size();
			double totalAmount = invoice.getTotalAmount();
			double balance = invoice.getBalance();

			System.out.printf("%-15s%-15s%-25s%-15s%-15s%-15s\n", invoiceNo, invoiceDate, customerName, numOfItems,
					totalAmount, balance);
		}
		System.out.println(
				"---------------------------------------------------------------------------------------------------");
	}
	// ----------------------------------------------------------------------
	public static void searchInvoice() {
		List<Invoice> invoices = shop.getInvoices();
		System.out.println("Enter invoice number to search: ");
		try {
			int invoiceNo = scanner.nextInt();
			scanner.nextLine(); // consume the newline character left by nextInt()
			Invoice invoice = null;
			for (Invoice inv : invoices) {
				if (inv.getId() == invoiceNo) {
					invoice = inv;
					break;
				}
			}
			if (invoice == null) {
				System.out.println("Invoice not found.");
				return;
			}
			System.out.println("Invoice #" + invoice.getId());
			System.out.println("Invoice date: " + invoice.getInvoiceDate());
			System.out.println("Customer name: " + invoice.getCustomerName());
			System.out.println("Number of items: " + invoice.getItems().size());
			System.out.println("--------------------------------------------------------------");
			System.out.printf("%-20s%-20s%-20s%-20s\n", "Item name", "Unit price", "Quantity", "Total price");
			System.out.println("--------------------------------------------------------------");
			for (Item item : invoice.getItems()) {
				System.out.printf("%-20s%-20.2f%-20d%-20.2f\n", item.getName(), item.getUnitPrice(), item.getQuantity(),
						item.getTotalPrice());
			}
			System.out.println("--------------------------------------------------------------");
			System.out.printf("%60s%-20.2f\n", "Total amount: ", invoice.getTotalAmount());
			System.out.printf("%60s%-20.2f\n", "Paid amount: ", invoice.getPaidAmount());
			System.out.printf("%60s%-20.2f\n", "Balance: ", invoice.getBalance());
		} catch (InputMismatchException e) {
			System.out.println("Invalid input. Please enter a valid invoice number.");
			scanner.nextLine(); // consume any remaining input
		}
	}
	// ----------------------------------------------------------------------
	public static ArrayList<Invoice> loadInvoices() {//not used
	    ArrayList<Invoice> invoices = new ArrayList<>();
	    try {
	        FileInputStream fis = new FileInputStream("invoice.ser");
	        ObjectInputStream ois = new ObjectInputStream(fis);
	        invoices = (ArrayList<Invoice>) ois.readObject();
	        ois.close();
	        fis.close();
	        System.out.println("Invoices loaded successfully.");
	    } catch (IOException | ClassNotFoundException ioe) {
	        ioe.printStackTrace();
	    }
	    return invoices;
	}
	// ----------------------------------------------------------------------
	public static ArrayList<Item> deserializeItems(String filename) {
	    try {
	        FileInputStream fis = new FileInputStream(filename);
	        ObjectInputStream ois = new ObjectInputStream(fis);
	        ArrayList<Item> items = (ArrayList<Item>) ois.readObject();
	        ois.close();
	        fis.close();
	        System.out.println(items.toString());
	        return items;
	    } catch (IOException ioe) {
	        ioe.printStackTrace();
	    } catch (ClassNotFoundException cne) {
	        cne.printStackTrace();
	    }
	    return null;
	}
	// ----------------------------------------------------------------------

}

package src;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class Main {
	private static ArrayList<Item> items = new ArrayList<>();
	static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		Shop shop = new Shop();
		ArrayList<Item> items = new ArrayList<Item>();

		while (true) {
			System.out.println("Main Menu:");
			System.out.println("1- Shop Settings");
			System.out.println("2- Manage Shop Items");
			System.out.println("3- Exit");

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
				scanner.nextLine(); // consume the newline character

				switch (shopChoice) {
				case 1:
					// load data
					break;
				case 2:
					System.out.println("Enter new shop name:");
					String newName = scanner.nextLine();
					shop.setShopName(newName);
					// save data
					break;
				case 3:// Set Invoice Header (Tel / Fax / Email / Website) (Data should be saved
					 shop.setInvoiceHeader();
					System.out.println(shop.toString()) ;
					    break;
					    // save data
				case 4:
					shop.loadInvoiceHeader();
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
				scanner.nextLine(); // consume the newline character

				switch (itemChoice) {
				case 1:
					// add item
					System.out.println("Enter item ID:");
					int id = scanner.nextInt();
					scanner.nextLine(); // consume the newline character
					System.out.println("Enter item name:");
					String name = scanner.nextLine();
					System.out.println("Enter item unit price:");
					double unitPrice = scanner.nextDouble();
					scanner.nextLine(); // consume the newline character
					System.out.println("Enter item quantity:");
					int quantity = scanner.nextInt();
					scanner.nextLine(); // consume the newline character
					Item item = new Item(id, name, unitPrice, quantity);
					items.add(item);
					try {
						FileOutputStream fos = new FileOutputStream("items.ser");
						ObjectOutputStream oos = new ObjectOutputStream(fos);
						oos.writeObject(items);
						oos.close();
						fos.close();
						System.out.println("Item added successfully.");
					} catch (IOException ioe) {
						ioe.printStackTrace();
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
			case 3:
				System.out.println("Exiting program...");
				return;
			default:
				System.out.println("Invalid choice.");
				break;
			}
		}
	}

	@SuppressWarnings("unchecked") // suppress unchecked cast warning
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
	}

	private static void printItems() {
		try {
			FileInputStream fis = new FileInputStream("items.ser");
			ObjectInputStream ois = new ObjectInputStream(fis);
			ArrayList<Item> items = (ArrayList<Item>) ois.readObject();
			ois.close();
			fis.close();
			for (Item item : items) {
				System.out.println(item);
			}
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private static void removeItem() {
		System.out.println("Enter item ID to remove:");
		int idToRemove = scanner.nextInt();
		boolean found = false;
		for (int i = 0; i < items.size(); i++) {
			if (items.get(i).getId() == idToRemove) {
				items.remove(i);
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
			oos.writeObject(items);
			oos.close();
			fos.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	private static void changePrice() {
		System.out.println("Enter item ID to change price:");
		int itemId = scanner.nextInt();
		scanner.nextLine(); // consume the newline character

		boolean itemFound = false;
		for (Item item : items) {
			if (item.getId() == itemId) {
				System.out.println("Enter new unit price:");
				double newPrice = scanner.nextDouble();
				scanner.nextLine(); // consume the newline character
				item.setUnitPrice(newPrice);
				itemFound = true;
				break;
			}
		}

		if (itemFound) {
			try {
				FileOutputStream fos = new FileOutputStream("items.ser");
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				oos.writeObject(items);
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

	private static void reportAllItems() {
		try {
			FileInputStream fis = new FileInputStream("items.ser");
			ObjectInputStream ois = new ObjectInputStream(fis);
			ArrayList<Item> items = (ArrayList<Item>) ois.readObject();
			ois.close();
			fis.close();
			System.out.println("All Items Report:");
			System.out.println("-----------------------");
			for (Item item : items) {
				System.out.println(item.toString());
			}
			System.out.println("-----------------------");
		} catch (IOException | ClassNotFoundException ioe) {
			ioe.printStackTrace();
		}
	}

	public static String[] loadInvoiceHeader() {
	    try {
	        FileInputStream fis = new FileInputStream("shop.ser");
	        ObjectInputStream ois = new ObjectInputStream(fis);
	        Shop shop = (Shop) ois.readObject();
	        ois.close();
	        fis.close();
	        return new String[]{shop.tel, shop.fax, shop.email, shop.website};
	    } catch (IOException | ClassNotFoundException e) {
	        e.printStackTrace();
	        return null;
	    }
	}



}

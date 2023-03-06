package src;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Shop implements Serializable{
	private String shopName;
	private String tel;
	private String fax;
	private String email;
	private String website;
	 List<Invoice> invoices;
	 List<Item> items;
	// Customer customer = new Customer();
	 public Shop() {
		    this.items = new ArrayList<Item>();
		    this.invoices = new ArrayList<Invoice>();
		}
	 public List<Invoice> getInvoices() {
		    return this.invoices;
		}
	 public List<Item> getItems() {
		    return this.items;
		}
	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public void setInvoiceHeader(String tel, String fax, String email, String website) {
		this.tel = tel;
		this.fax = fax;
		this.email = email;
		this.website = website;
	}

	public String getInvoiceHeader() {
		return "Tel: " + tel + "\nFax: " + fax + "\nEmail: " + email + "\nWebsite: " + website;
	}
	public String toString() {
	    return "Tel: " + this.tel + "\nFax: " + this.fax + "\nEmail: " + this.email + "\nWebsite: " + this.website;
	}

	public void setInvoiceHeader() {
	    Scanner scanner = new Scanner(System.in);
	    System.out.println("Enter new invoice header information:");
	    System.out.print("Tel: ");
	    String tel = scanner.nextLine();
	    System.out.print("Fax: ");
	    String fax = scanner.nextLine();
	    System.out.print("Email: ");
	    String email = scanner.nextLine();
	    System.out.print("Website: ");
	    String website = scanner.nextLine();
	    setInvoiceHeader(tel, fax, email, website);
	    try {
	        FileOutputStream fos = new FileOutputStream("shop.ser");
	        ObjectOutputStream oos = new ObjectOutputStream(fos);
	        oos.writeObject(this);
	        oos.close();
	        fos.close();
	        System.out.println("Shop data saved.");
	    } catch (IOException ioe) {
	        ioe.printStackTrace();
	    }
	}

	public static void loadInvoiceHeader() {
	    try {
	        FileInputStream fis = new FileInputStream("shop.ser");
	        ObjectInputStream ois = new ObjectInputStream(fis);
	        Shop shop = (Shop) ois.readObject();
	        ois.close();
	        fis.close();
	        String tel = shop.getTel();
	        String fax = shop.getFax();
	        String email = shop.getEmail();
	        String website = shop.getWebsite();
	        System.out.println("Invoice Header Information:");
	        System.out.println("Tel: " + tel);
	        System.out.println("Fax: " + fax);
	        System.out.println("Email: " + email);
	        System.out.println("Website: " + website);
	    } catch (IOException ioe) {
	        ioe.printStackTrace();
	    } catch (ClassNotFoundException cne) {
	        cne.printStackTrace();
	    }
	}

	
	private void loadData() {
	    try {
	        FileInputStream fis = new FileInputStream("shop.ser");
	        ObjectInputStream ois = new ObjectInputStream(fis);
	        Shop shop = (Shop) ois.readObject();
	        ois.close();
	        fis.close();

	        this.shopName = shop.shopName;
	        this.tel = shop.tel;
	        this.fax = shop.fax;
	        this.email = shop.email;
	        this.website = shop.website;
	        this.items = shop.items;
	        this.invoices = shop.invoices; 
	    } catch (IOException ioe) {
	        System.out.println("Error loading shop data: " + ioe.getMessage());
	    } catch (ClassNotFoundException c) {
	        System.out.println("Class not found: " + c.getMessage());
	    }
	}
	public static void saveData(List<Invoice> invoices) {
	    try {
	        FileOutputStream fos = new FileOutputStream("invoices.ser");
	        ObjectOutputStream oos = new ObjectOutputStream(fos);
	        oos.writeObject(invoices);
	        oos.close();
	        fos.close();
	        System.out.println("Invoices saved.");
	    } catch (IOException ioe) {
	        ioe.printStackTrace();
	    }
	}


}

package src;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Invoice implements Serializable{
    private int id;
    private String customerName;
    private String phoneNumber;
    private Date invoiceDate;
    private ArrayList<Item> items;
    private double totalAmount;
    private double paidAmount;
    private double balance;

    public Invoice( int id ,String customerName, String phoneNumber, Date invoiceDate,
			ArrayList<Item> items, double totalAmount, double paidAmount, double balance) {
    	this.id = id;
		this.customerName = customerName;
		this.phoneNumber = phoneNumber;
		this.invoiceDate = invoiceDate;
		this.items = items;
		this.totalAmount = totalAmount;
		this.paidAmount = paidAmount;
		this.balance = balance; 
	}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

    public List<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public double getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(double paidAmount) {
        this.paidAmount = paidAmount;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getTotalPrice() {
        double totalPrice = 0.0;
        for (Item item : items) {
            totalPrice += item.getUnitPrice() * item.getQuantity();
        }
        return totalPrice;
    }


	public void printInvoice() {
		System.out.println("InvoiceNO: " + id);
		System.out.println("Customer Name: " + customerName);
		System.out.println("Phone Number: " + phoneNumber);
		System.out.println("Invoice Date: " + getInvoiceDate());
		if (items != null) {
		    System.out.println("Items:");
		    for (Item item : items) {
		        System.out.println(" - " + item.getName() + " (" + item.getQuantity() + " x " + item.getUnitPrice() + ") = " + item.getTotalPrice());
		    }
		    System.out.println("Total Amount: " + totalAmount);
		    System.out.println("Paid Amount: " + paidAmount);
		    System.out.println("Balance: " + balance);
		}
		else {
			System.out.println("items in null");
		}

		
	}


    
}

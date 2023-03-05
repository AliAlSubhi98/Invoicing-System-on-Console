package src;

import java.util.Date;
import java.util.List;

public class Invoice {
    private int id;
    private String customerName;
    private String phoneNumber;
    private Date invoiceDate;
    private List<Item> items;
    private double totalAmount;
    private double paidAmount;
    private double balance;



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

    public void setItems(List<Item> items) {
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

    public void addItem(Item item) {
        items.add(item);
    }


    
}

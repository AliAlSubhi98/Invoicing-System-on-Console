package src;

import java.io.Serializable;

public class Item implements Serializable{
    private int id;
    private String name;
    private double unitPrice;
    private int quantity;
    private double qty;

    public Item(int id, String name, double unitPrice, int quantity , double qty) {
        this.id = id;
        this.name = name;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.qty = qty;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", unitPrice=" + unitPrice +
                ", quantity=" + quantity +
                ", QTY=" + qty +
                '}';
    }
    public double getTotalPrice() {
		double totalPrice = unitPrice * quantity;
		return totalPrice;
	}

	public double getQty() {
		return qty;
	}

	public void setQty(double qty) {
		qty = unitPrice * quantity;
		this.qty = qty;
	}
}

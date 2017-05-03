package entities;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class CartItem {
	
	// fields
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private Date timeOut;
	
	private Date timeIn;
	
	private Double total;
	
	@ManyToOne
	@JoinColumn(name="cartId")
	private Cart cart;
	
	@ManyToOne
	@JoinColumn(name="inventoryItemId")
	private InventoryItem inventoryItem;

	// toString
	public Date getTimeOut() {
		return timeOut;
	}

	public void setTimeOut(Date timeOut) {
		this.timeOut = timeOut;
	}

	public Date getTimeIn() {
		return timeIn;
	}

	public void setTimeIn(Date timeIn) {
		this.timeIn = timeIn;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public InventoryItem getInventoryItem() {
		return inventoryItem;
	}

	public void setInventoryItem(InventoryItem iventoryItem) {
		this.inventoryItem = iventoryItem;
	}

	public int getId() {
		return id;
	}

	// toString
	@Override
	public String toString() {
		return "CartItem [id=" + id + ", timeOut=" + timeOut + ", timeIn=" + timeIn + ", total=" + total + ", cart="
				+ cart + ", iventoryItem=" + inventoryItem + "]";
	}
}

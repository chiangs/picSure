package entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

@Entity
public class Cart {

	// fields
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private int inventoryId;

	private double total;

	@OneToOne
	@JoinColumn(name = "userId")
	private User user;

	@ManyToMany
	@JoinTable(name = "cartItems", joinColumns = @JoinColumn(name = "cartId"), inverseJoinColumns = @JoinColumn(name = "inventoryItemId"))
	private List<InventoryItem> inventoryItems;

	// gets and sets
	public int getInventoryId() {
		return inventoryId;
	}

	public void setInventoryId(int inventoryId) {
		this.inventoryId = inventoryId;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<InventoryItem> getInventoryItems() {
		return inventoryItems;
	}

	public void setInventoryItems(List<InventoryItem> inventoryItems) {
		this.inventoryItems = inventoryItems;
	}

	public int getId() {
		return id;
	}

	// toString
	@Override
	public String toString() {
		return "Cart [id=" + id + ", inventoryId=" + inventoryId + ", total=" + total + ", user=" + user
				+ ", inventoryItems=" + inventoryItems + "]";
	}	
}

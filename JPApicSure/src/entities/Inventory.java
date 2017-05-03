package entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Inventory {
	
	// fields
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private int storeId;

	@OneToOne
	@JoinColumn(name="storeId")
	private Store store;
	
	@OneToMany(mappedBy="inventory")
	private List<InventoryItem> iventoryItems;

	// gets and sets
	public int getStoreId() {
		return storeId;
	}

	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public List<InventoryItem> getIventoryItems() {
		return iventoryItems;
	}

	public void setIventoryItems(List<InventoryItem> iventoryItems) {
		this.iventoryItems = iventoryItems;
	}

	public int getId() {
		return id;
	}

	// toString
	@Override
	public String toString() {
		return "Inventory [id=" + id + ", storeId=" + storeId + ", store=" + store + ", iventoryItems=" + iventoryItems
				+ "]";
	}
	

}

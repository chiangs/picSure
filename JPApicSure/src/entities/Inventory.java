package entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Inventory {
	
	// fields
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@JsonBackReference
	@OneToOne
	@JoinColumn(name="storeId")
	private Store store;
	
	@JsonManagedReference(value="inventoryToinvItem")
	@OneToMany(mappedBy="inventory", fetch= FetchType.EAGER)
	private List<InventoryItem> iventoryItems;
	
	// gets and sets
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

}

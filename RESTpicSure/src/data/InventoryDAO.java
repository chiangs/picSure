package data;

import entities.Inventory;

public interface InventoryDAO {
	public Inventory show(Integer id);
	public Inventory create(Integer id, Inventory i);
}

package data;

import entities.Inventory;

public interface InventoryDAO {
	public Inventory show(Integer id);
	public Inventory update(Integer id, Inventory i);
	public Inventory create(Integer id, Inventory i);
	public Boolean destroy(Integer id);
}

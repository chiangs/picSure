package data;

import entities.Inventory;

public interface InventoryDAO {
	public Inventory show(Integer id);
	public Inventory update(Integer id, Inventory c);
	public Inventory create(Integer id, Inventory c);
	public Inventory destroy(Integer id);
}

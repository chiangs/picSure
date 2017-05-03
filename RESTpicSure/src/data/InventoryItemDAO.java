package data;

import entities.InventoryItem;

public interface InventoryItemDAO {
	public InventoryItem show(Integer id);
	public InventoryItem update(Integer id, InventoryItem c);
	public InventoryItem create(Integer id, InventoryItem c);
	public InventoryItem destroy(Integer id);
}

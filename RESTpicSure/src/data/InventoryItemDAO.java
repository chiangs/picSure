package data;

import entities.InventoryItem;

public interface InventoryItemDAO {
	public InventoryItem show(Integer id);
	public InventoryItem update(Integer id, InventoryItem i);
	public InventoryItem create(Integer storeId, Integer equipmentId, InventoryItem i);
	public Boolean destroy(Integer id);
}

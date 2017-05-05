package data;

import java.util.List;

import entities.InventoryItem;

public interface InventoryItemDAO {
	public InventoryItem show(Integer id);
	public List<InventoryItem> index(Integer id);
	public InventoryItem update(Integer id, InventoryItem i);
	public InventoryItem create(Integer inventoryId, Integer equipmentId);
	public Boolean destroy(Integer id);
}

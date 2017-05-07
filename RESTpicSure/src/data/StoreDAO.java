package data;

import java.util.List;

import entities.Inventory;
import entities.Store;

public interface StoreDAO {
	public List<Store> index();
	public List<Store> indexEquipment(Integer equipmentId);
	public Store show(Integer id);
	public Inventory showInventory(Integer id);
	public Store update(Integer id, Store s);
	public Store create(Integer id, Store s);
	public Boolean destroy(Integer id);
}

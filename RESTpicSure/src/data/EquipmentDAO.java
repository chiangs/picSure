package data;

import java.util.List;

import entities.Equipment;

public interface EquipmentDAO {
	public List<Equipment> index ();
	public Equipment show(Integer id);
	public Equipment update(Integer id, Equipment e);
	public Equipment create(Integer id, Equipment e);
	public Boolean destroy(Integer id);
}

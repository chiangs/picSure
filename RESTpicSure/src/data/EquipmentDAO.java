package data;

import entities.Equipment;

public interface EquipmentDAO {
	public Equipment show(Integer id);
	public Equipment update(Integer id, Equipment e);
	public Equipment create(Integer id, Equipment e);
	public Boolean destroy(Integer id);
}

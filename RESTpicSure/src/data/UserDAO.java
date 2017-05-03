package data;

import entities.Equipment;

public interface UserDAO {
	public Equipment show(Integer id);
	public Equipment update(Integer id, Equipment c);
	public Equipment create(Integer id, Equipment c);
	public Equipment destroy(Integer id);
}

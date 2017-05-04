package data;

import entities.Address;
import entities.Store;

public interface StoreDAO {
	public Store show(Integer id);
	public Store update(Integer id, Store s);
	public Store create(Integer id, Store s, Address a);
	public Boolean destroy(Integer id);
}

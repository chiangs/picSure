package data;

import java.util.List;

import entities.Address;

public interface AddressDAO {
	public Address show(Integer id);
	public Address update(Integer id, Address a);
	public Address createUserAddress(Integer id, Address a);
	public Address createStoreAddress(Integer id, Address a);
	public Boolean destroy(Integer id);
}

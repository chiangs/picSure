package data;

import entities.Address;
import entities.User;

public interface UserDAO {
	public User show(Integer id);
	public User update(Integer id, User u);
	public User create(Integer id, User u, Address a);
	public Boolean destroy(Integer id);
}

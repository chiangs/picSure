package data;

import entities.User;

public interface UserDAO {
	public User show(Integer id);
	public User update(Integer id, User c);
	public User create(User c);
	public Boolean destroy(Integer id);
}

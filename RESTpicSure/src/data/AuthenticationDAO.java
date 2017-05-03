package data;

import entities.User;

public interface AuthenticationDAO {
	public User register(User u);

	public User authenticateUser(User u);
}

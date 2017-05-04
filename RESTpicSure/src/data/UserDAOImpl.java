package data;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import entities.User;

@Transactional
@Repository
public class UserDAOImpl implements UserDAO {

	@PersistenceContext
	private EntityManager em;

	@Override
	public User show(Integer id) {
		return em.find(User.class, id);
	}

	@Override
	public User update(Integer id, User u) {
		User user = em.find(User.class, id);
		user.setfName(u.getfName());
		user.setlName(u.getlName());
		user.setAddress(u.getAddress());
		user.setPassword(u.getPassword());
		user.setEmail(u.getEmail());

		return user;
	}

	@Override
	public User create(User u) {
		return null;
	}

	@Override
	public Boolean destroy(Integer id) {
		try {
			em.remove(em.find(User.class, id));
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
package data;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import entities.User;

@Transactional
@Repository
public class UserDAOImpl implements UserDAO {

	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private CartDAO cartDAO;

	@Override
	public User show(Integer id) {
		return em.find(User.class, id);
	}

	@Override
	public User update(Integer id, User u) {
		User user = em.find(User.class, id);
		user.setfName(u.getfName());
		user.setlName(u.getlName());
		user.setPassword(u.getPassword());
		user.setEmail(u.getEmail());

		return user;
	}

	@Override
		
	public User create(User u) {
		
		em.persist(u);
		em.flush();
//		cartDAO.create(u.getId());
		
		return u;
	}

	@Override
	public Boolean destroy(Integer id) {
		try {
			em.find(User.class, id).setActive(false);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
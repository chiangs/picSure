package data;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import entities.Cart;
import entities.User;

@Transactional
@Repository
public class CartDAOImpl implements CartDAO {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Cart show(Integer id) {
		String q = "SELECT c FROM Cart c Where c.user.id = :id";
		
		return em.createQuery(q, Cart.class).setParameter("id", id).getSingleResult();
	}

	@Override
	public Cart create(Integer id) {
		Cart c = new Cart();
		c.setUser(em.find(User.class, id));
		em.persist(c);
		em.flush();
		return c;
	}

}

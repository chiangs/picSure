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
	public Cart create(Integer userId) {
		Cart c = new Cart();
		c.setUser(em.find(User.class, userId));
		em.persist(c);
		em.flush();
		return c;
	}
	
	@Override
	public Cart empty(Integer userId) {
		User u = em.find(User.class, userId);
		Cart c = u.getCart();
		for (int i = 0; i < c.getCartItems().size(); i++) {
			c.getCartItems().remove(i);
		}
		em.persist(c);
		em.flush();
		return c;
	}

}

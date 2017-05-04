package data;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import entities.Cart;
import entities.CartItem;
import entities.Reservation;
import entities.User;

@Transactional
@Repository
public class CartDAOImpl implements CartDAO {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Cart show(Integer id) {
		String q = "SELECT c FROM Cart c WHERE c.user.id = :id";
		return em.createQuery(q, Cart.class).setParameter("id", id).getSingleResult();
	}

	@Override
	public Cart update(Integer userId, Cart c) {
		User u = em.find(User.class, userId);
		Cart cart = em.find(Cart.class, u.getCart().getId());
		cart.setCartItems(c.getCartItems());
		return cart;
	}

	@Override
	public Cart create(Integer id, Cart c) {
		List<CartItem> items = new ArrayList<>();
		User u = em.find(User.class, id);
		c.setCartItems(items);
		c.setUser(u);
		em.persist(c);
		em.flush();
		return c;
	}

}

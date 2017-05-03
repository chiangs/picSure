package data;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import entities.Cart;
import entities.InventoryItem;
import entities.User;

@Transactional
@Repository
public class CartDAOImpl implements CartDAO {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Cart show(Integer id) {
		return em.find(Cart.class, id);
	}

	@Override
	public Cart update(Integer id, Cart c) {
		Cart cart = em.find(Cart.class, id);
		cart.setInventoryItems(c.getInventoryItems());
		return cart;
	}

	@Override
	public Cart create(Integer id, Cart c) {
		List<InventoryItem> items = new ArrayList<>();
		User u = em.find(User.class, id);
		c.setInventoryItems(items);
		c.setUser(u);
		em.persist(c);
		em.flush();
		return c;
	}

}
